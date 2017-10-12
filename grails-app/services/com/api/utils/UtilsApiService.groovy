package com.api.utils

import java.text.ParseException
import java.text.SimpleDateFormat

import org.codehaus.groovy.grails.commons.*
import org.json.JSONObject

import ar.com.goliath.*
import ar.com.goliath.business.BusinessModel
import ar.com.goliath.business.UserBusinessModel
import ar.com.operation.BUSINESSMODEL

import com.*
class UtilsApiService {

	static transactional = true

	public static JSONObject isInAvailableCity(String place){
		def placeJson = grails.converters.JSON.parse(place)
		def cities=EnabledCities.findAllByEnabled(true)
		boolean isInCityEnabled=false;
		for (EnabledCities cit :cities){

			if(placeJson?.city.contains(cit?.admin1Code.trim())||cit?.admin1Code.contains(placeJson?.city.trim())){
				isInCityEnabled=true
			}
		}
		return isInCityEnabled?placeJson:null;
	}
	public String generateAddress( place){
		def config = ConfigurationHolder.config
		String placeConfig= config.create.trip.generic
		def ffx=placeConfig
		ffx=ffx.replaceAll("#NUMBER#", place.number)
		ffx=ffx.replaceAll("#STREET#", place.street)
		ffx=ffx.replaceAll("#CITY#",place.city)
		ffx=ffx.replaceAll("#COUNTRY#",place.country)
		ffx=ffx.replaceAll("#SCOUNTRY#",place.ccode)
		ffx=ffx.replaceAll("#LNG#",place.lng)
		ffx=ffx.replaceAll("#LAT#",place.lat)
		return ffx
	}
	public String  generateFormatedAddressToClient( dateToConvert,user){
		if (dateToConvert == null){
			return ""
		}
		TimeZone userTimeZone=null
		if(user?.city?.timeZone){
			userTimeZone = TimeZone.getTimeZone(user?.city?.timeZone);
		}else if(user?.rtaxi?.city?.timeZone){
			userTimeZone = TimeZone.getTimeZone(user?.rtaxi?.city?.timeZone);
		}else{
			log.error "UtilsApiService:generateFormatedAddress Ususario sin TimeZone "+user.username
		}
		def config = ConfigurationHolder.config
		TimeZone serverTimeZone = TimeZone.getTimeZone(config.server.timezone);
		Calendar serverTimeZoneCalendar = new GregorianCalendar(serverTimeZone);
		serverTimeZoneCalendar.setTime(dateToConvert)
		
		//def timezone =convertToNewTimeZone(dateToConvert,serverTimeZone,userTimeZone)
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatoDelTexto.setTimeZone(userTimeZone);
		return formatoDelTexto.format(serverTimeZoneCalendar.getTime());
	}
	public Date generateFormatedAddressToServer(String dateToConvert,user){
		def time_user = User.get(user.id)
		TimeZone userTimeZone=null
		if(!time_user){
			log.error "UtilsApiService:generateFormatedAddress USER NOT FOUND "+user
			return null
		}else if(time_user?.rtaxi){
			if(!time_user?.rtaxi?.city?.timeZone){
				log.error "UtilsApiService:generateFormatedAddress TIMEZONE NOT FOUND FOR RTAXI "+user
				return null
			}
			log.error "ADMIN TIMEZONE:" + time_user?.email
			
			userTimeZone = TimeZone.getTimeZone(time_user?.rtaxi?.city?.timeZone);
		}else{
			if(!time_user?.city?.timeZone){
				log.error "UtilsApiService:generateFormatedAddress TIMEZONE NOT FOUND FOR RTAXI "+user
				return null
			}
			log.error "USER TIMEZONE:" + time_user?.email
			
			userTimeZone = TimeZone.getTimeZone(time_user?.city?.timeZone);
		}
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatoDelTexto.setTimeZone(userTimeZone)
		Date fecha = null;
		try {
			fecha = formatoDelTexto.parse(dateToConvert);
		} catch (ParseException ex) {
			log.error "UtilsApiService:generateFormatedAddress fecha Incorrecta "+dateToConvert+" "+user.username
		}
		
		log.error "----------------chech Date--------------------"
		log.error formatoDelTexto.format(fecha)
		log.error userTimeZone
		def config = ConfigurationHolder.config
		TimeZone serverTimeZone = TimeZone.getTimeZone(config.server.timezone);
		Date convertedDate=convertToNewTimeZone(fecha,userTimeZone,serverTimeZone);
		log.error formatoDelTexto.format(convertedDate)
		log.error "----------------chech Date--------------------"
		Calendar serverTimeZoneCalendar = new GregorianCalendar();
		serverTimeZoneCalendar.setTime(fecha)
		
		log.error formatoDelTexto.format(serverTimeZoneCalendar.getTime())
		
		formatoDelTexto.setTimeZone(TimeZone.getTimeZone("UTC"));
		log.error formatoDelTexto.format(serverTimeZoneCalendar.getTime())
		formatoDelTexto.setTimeZone(TimeZone.getTimeZone("America/Araguaina"));
		log.error formatoDelTexto.format(serverTimeZoneCalendar.getTime())
		formatoDelTexto.setTimeZone(TimeZone.getTimeZone("America/Argentina/Mendoza"));
		log.error formatoDelTexto.format(serverTimeZoneCalendar.getTime())
		formatoDelTexto.setTimeZone(serverTimeZone);
		log.error formatoDelTexto.format(serverTimeZoneCalendar.getTime())
		return formatoDelTexto.parse(formatoDelTexto.format(serverTimeZoneCalendar.getTime()));
	}
	/**
	 * Adapt calendar to client time zone.
	 * @param calendar - adapting calendar
	 * @param timeZone - client time zone
	 * @return adapt calendar to client time zone
	 */
	public static Calendar convertCalendar(final Calendar calendar, final TimeZone timeZone) {
		Calendar ret = new GregorianCalendar(timeZone);
		ret.setTimeInMillis(calendar.getTimeInMillis() +
				timeZone.getOffset(calendar.getTimeInMillis()) -
				TimeZone.getDefault().getOffset(calendar.getTimeInMillis()));
		ret.getTime();
		return ret;
	}
	public Date convertToNewTimeZone(Date date, TimeZone oldTimeZone, TimeZone newTimeZone){
		long oldDateinMilliSeconds=date.time - oldTimeZone.rawOffset

		Date dateInGMT=new Date(oldDateinMilliSeconds)

		long convertedDateInMilliSeconds = dateInGMT.time + newTimeZone.rawOffset
		Date convertedDate = new Date(convertedDateInMilliSeconds)

		return convertedDate
	}
	public Device setDevice(String device,String keyValue,User usr){
		Device.findAllByUser(usr).each{ it.delete() }
		def devic=new Device()
		if(device!=null && keyValue!=null){
			devic.keyValue=keyValue
			devic.description="                 "
			devic.dev=device
			devic.user=usr
			devic.save(flush:true)
		}else{
			devic.keyValue="sin key osea web"
			devic.description="  "
			devic.dev=UserDevice.WEB
			devic.user=usr
			devic.save(flush:true)
		}
		return devic
	}
	public setBusinessModel(User usr,String business){
		def bs=business? BusinessModel.findByName(business): BusinessModel.findByName(BUSINESSMODEL.GENERIC)
		
		if(bs){
			if (!usr.businessModel.contains(bs)) {
				UserBusinessModel.create usr, bs
			}
		}
	}
	
	public setBusinessModelByRtaxi(User usr,User rtaxi){
		
		if (! usr.businessModel.contains(rtaxi.businessModel[0])) {
			UserBusinessModel.create  usr, rtaxi.businessModel[0]
		}
		
	}
	
	public getBusiness(User usr){
		def bs =usr.businessModel[0]
		if (!bs)
			return  BUSINESSMODEL.GENERIC
		else
			println"-------------${bs.name}"
			return bs.name
	}
	public boolean checkUserPermisson(usr){
		def adminRole=Role.findByAuthority("ROLE_ADMIN")
		def observerRole=Role.findByAuthority("ROLE_MONITOR")
		def companyRole=Role.findByAuthority("ROLE_COMPANY")
		def operadorRole=Role.findByAuthority('ROLE_OPERATOR')
		def telephonistRole=Role.findByAuthority('ROLE_TELEPHONIST')
		def investorRole=Role.findByAuthority('ROLE_INVESTOR')
		def corporateRole=Role.findByAuthority('ROLE_COMPANY_ACCOUNT')
		def corporateUserRole=Role.findByAuthority('ROLE_COMPANY_ACCOUNT_EMPLOYEE')
		if (!usr.authorities.contains(adminRole)&&!usr.authorities.contains(operadorRole)&&
		!usr.authorities.contains(companyRole)&&!usr.authorities.contains(telephonistRole)
		&&!usr.authorities.contains(investorRole)&&!usr.authorities.contains(observerRole)
		&&!usr.authorities.contains(corporateRole)
		&&!usr.authorities.contains(corporateUserRole)){
			return true
		}else{
			return false
		}
	}
}
