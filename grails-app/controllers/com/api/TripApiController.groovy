package com.api
import grails.converters.JSON

import javax.servlet.*
import javax.servlet.http.*

import org.codehaus.groovy.grails.commons.*
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import ar.com.favorites.*
import ar.com.goliath.*
import ar.com.goliath.corporate.CorporateUser
import ar.com.operation.DelayOperation
import ar.com.operation.Operation
import ar.com.operation.OperationCompanyHistory
import ar.com.operation.OperationCompanyPending
import ar.com.operation.OperationHistory
import ar.com.operation.OperationPending
import ar.com.operation.TRANSACTIONSTATUS
import ar.com.operation.TrackOperation

import com.*
class TripApiController {
	def exportService
	def springSecurityService
	def placeService
	def placeCompanyAccountEmployeeService
	def userService
	def emailService
	def notificationService
	def utilsApiService
	// the delete, save and update actions only accept POST requests
	//static allowedMethods = [createTrip:'POST']
	def geocoderService
	
	def createTrip={
		//		response.contentType = "text/json;charset=UTF-8"
		render(contentType: 'text/json',encoding:"UTF-8") { status=11 message="deprecated" }
//		try{
//			def usr=null
//
//			def tok=null
//			if(params?.token){
//				tok=PersistToken.findByToken(params?.token)
//				if(tok){
//					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
//				}
//			}else if (springSecurityService.isLoggedIn()){
//				usr = springSecurityService.currentUser
//
//			}
//			if(tok || springSecurityService.isLoggedIn()){
//				log.debug "con token existe el usuario ?"+usr
//				if(usr){
//					def place1=params?.placeFrom
//					def place2=params?.placeTo
//					def piso=params?.piso?:""
//					def dpto=params?.departamento?:""
//					def comen=params?.comentarios?:""
//					def favName=params?.favoriteName?:"temp"
//					boolean isfav=params?.isFavorite?!params?.isFavorite.contains("true"):true
//					Device.findAllByUser(usr).each{ it.delete() }
//					def devic=new Device()
//					if(params?.dev && params?.keyValue){
//						devic.keyValue=params?.keyValue
//						devic.description="                 "
//						devic.dev=params?.dev
//						devic.user=usr
//						devic.save(flush:true)
//					}else{
//						devic.keyValue="sin key osea web"
//						devic.description="  "
//						devic.dev=UserDevice.WEB
//						devic.user=usr
//						devic.save(flush:true)
//					}
//					log.error "-------------------FEDE-----------"
//					log.error params?.keyValue
//					log.error params?.dev
//					log.error "-------------FEDE-----------------"
//					if(place1){
//						log.debug "con token y usr  existe place1 ?"+place1
//
//						def o = grails.converters.JSON.parse(place1)
//						def pl = new Place(o)
//						pl.json = place1
//						def cities=EnabledCities.findAllByEnabled(true)
//						boolean isInCityEnabled=false;
//						if(pl!=null && pl?.admin1Code){
//							for (EnabledCities cit :cities){
//								if(pl.admin1Code.contains(cit.admin1Code)||cit.admin1Code.contains(pl.admin1Code)){
//									log.debug cit.admin1Code
//									log.debug pl.admin1Code
//									isInCityEnabled=true
//								}
//							}
//
//						}
//
//						if(isInCityEnabled && pl.streetNumber!=null  ){
//							log.debug "esta la ciudad habilitada y tiene una calle correcta"
//							try{
//								log.debug "comenzando a crear el viaje"
//								def oper=null
//								if(usr instanceof CorporateUser){
//									oper=placeCompanyAccountEmployeeService.createFavoriteAndOperation(usr,place1,place2,isfav,piso,dpto,comen,favName,params?.placeinput2,devic.dev)
//
//								}else{
//									oper=placeService.createFavoriteAndOperation(usr,place1,place2,isfav,piso,dpto,comen,favName,params?.placeinput2,devic.dev)
//
//								}
//
//
//								def conf = SpringSecurityUtils.securityConfig
//								if(!usr?.isTestUser){
//									def stringRtaxi=usr?.rtaxi?.companyName?usr?.rtaxi?.companyName:''
//									emailService.send("rrhh@dinerotaxi.com",conf.ui.register.emailFrom, "${stringRtaxi} NUEVO VIAJE,MOBILE ${devic.dev} /n ${usr.firstName} /n ${usr.phone} /n ${usr.email}", usr.email)
//								}
//								//notificationService.notificateOnCreateTrip(oper,usr)
//								render(contentType: 'text/json',encoding:"UTF-8") {
//									id=oper.id
//									status=100
//								}
//							}catch (Exception e){
//								log.error(e.getMessage())
//								log.error(e.getCause())
//								render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//							}
//						}else if( pl.streetNumber==null ){
//							render(contentType: 'text/json',encoding:"UTF-8") { status=9 }
//
//						}else{
//							render(contentType: 'text/json',encoding:"UTF-8") { status=8 }
//						}
//
//					}else{
//						render(contentType: 'text/json',encoding:"UTF-8") { status=126 }
//					}
//				}else{
//					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
//				}
//			}else{
//				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
//			}
//		}catch (Exception e){
//			log.error e.getMessage()
//			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
//		}
	}
	def createTripGeo={
		//		response.contentType = "text/json;charset=UTF-8"
		render(contentType: 'text/json',encoding:"UTF-8") { status=11 message="deprecated" }
//		log.error params as JSON
//		try{
//			def usr=null
//
//			def tok=null
//			if(params?.token){
//				tok=PersistToken.findByToken(params?.token)
//				if(tok){
//					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
//				}
//			}else if (springSecurityService.isLoggedIn()){
//				usr = springSecurityService.currentUser
//
//			}
//			if(!tok && !springSecurityService.isLoggedIn()){
//				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
//				return
//			}
//			if(!usr){
//				render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
//			}
//			def placeFrom
//			if(params?.placeFrom){
//				
//				placeFrom = grails.converters.JSON.parse(params?.placeFrom)
//				def cities=EnabledCities.findAllByEnabled(true)
//				boolean isInCityEnabled=false;
//				for (EnabledCities cit :cities){
//					
//					if(placeFrom?.city.contains(cit?.admin1Code.trim())||cit?.admin1Code.contains(placeFrom?.city.trim())){
//						isInCityEnabled=true
//					}
//				}
//				if(!isInCityEnabled){
//					render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//					return;
//				}
//			}else{
//				render(contentType: 'text/json',encoding:"UTF-8") { status=126 }
//				return;
//			}
//			log.error "con token existe el usuario ?"+usr
//			def place2=params?.placeTo
//			def piso=params?.piso?:""
//			def dpto=params?.departamento?:""
//			def comen=params?.comentarios?:""
//			def favName=params?.favoriteName?:"temp"
//			boolean isfav=params?.isFavorite?!params?.isFavorite.contains("true"):true
//			Device.findAllByUser(usr).each{ it.delete() }
//			def devic=new Device()
//			if(params?.dev && params?.keyValue){
//				devic.keyValue=params?.keyValue
//				devic.description="                 "
//				devic.dev=params?.dev
//				devic.user=usr
//				devic.save(flush:true)
//			}else{
//				devic.keyValue="sin key osea web"
//				devic.description="  "
//				devic.dev=UserDevice.WEB
//				devic.user=usr
//				devic.save(flush:true)
//			}
//			def config = ConfigurationHolder.config
//			String placeConfig= config.create.trip.generic
//			def ffx=placeConfig
//			ffx=ffx.replaceAll("#NUMBER#", placeFrom.number)
//			ffx=ffx.replaceAll("#STREET#", placeFrom.street)
//			ffx=ffx.replaceAll("#CITY#",placeFrom.city)
//			ffx=ffx.replaceAll("#COUNTRY#",placeFrom.country)
//			ffx=ffx.replaceAll("#SCOUNTRY#",placeFrom.ccode)
//			ffx=ffx.replaceAll("#LNG#",placeFrom.lng)
//			ffx=ffx.replaceAll("#LAT#",placeFrom.lat)
//			
//			String placeTo=null
//			if(params?.placeTo){
//				def placeToJson=grails.converters.JSON.parse(params?.placeTo)
//				placeTo=placeConfig
//				placeTo=placeTo.replaceAll("#NUMBER#", placeToJson.number)
//				placeTo=placeTo.replaceAll("#STREET#", placeToJson.street)
//				placeTo=placeTo.replaceAll("#CITY#",placeToJson.city)
//				placeTo=placeTo.replaceAll("#COUNTRY#",placeToJson.country)
//				placeTo=placeTo.replaceAll("#SCOUNTRY#",placeToJson.ccode)
//				placeTo=placeTo.replaceAll("#LNG#",placeToJson.lng)
//				placeTo=placeTo.replaceAll("#LAT#",placeToJson.lat)
//			}
//			try{
//				def oper=null
//				if(usr instanceof CorporateUser){
//
//					oper=placeCompanyAccountEmployeeService.createFavoriteAndOperation(usr,ffx,placeTo,isfav,piso,dpto,comen,favName,params?.placeinput2,devic.dev)
//
//				}else{
//					oper=placeService.createFavoriteAndOperation(usr,ffx,placeTo,isfav,piso,dpto,comen,favName,null,devic.dev)
//
//				}
//				//notificationService.notificateOnCreateTrip(oper,usr)
//				def tr
//				tr=new com.TripCommand(oper.id,null,oper.createdDate,oper.favorites?.placeFrom.name,
//						oper.favorites?.placeFromPso?oper.favorites?.placeFromPso:'',
//						oper.favorites?.placeFromDto?oper.favorites?.placeFromDto:'',
//						oper.favorites?.placeTo?oper.favorites?.placeTo.name:'',
//						oper.favorites?.comments?oper.favorites?.comments:'',oper.status.toString(),new TaxiCommand())
//				render(contentType: 'text/json',encoding:"UTF-8") {
//					status=100
//					trip=tr
//				}
//			}catch (Exception e){
//				log.error(e.getMessage())
//				log.error(e.getCause())
//				render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//			}
//
//		}catch (Exception e){
//			log.error e.getMessage()
//			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
//		}
	}
	def createTripWithResponse={
		render(contentType: 'text/json',encoding:"UTF-8") { status=11 message="deprecated" }
		//		response.contentType = "text/json;charset=UTF-8"
//		try{
//			def usr=null
//
//			def tok=null
//			if(params?.token){
//				tok=PersistToken.findByToken(params?.token)
//				if(tok){
//					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
//				}
//			}else if (springSecurityService.isLoggedIn()){
//				usr = springSecurityService.currentUser
//
//			}
//			if(tok || springSecurityService.isLoggedIn()){
//				if(usr){
//					def place1=params?.placeFrom
//					def place2=params?.placeTo
//					def piso=params?.piso?:""
//					def dpto=params?.departamento?:""
//					def comen=params?.comentarios?:""
//					def favName=params?.favoriteName?:"temp"
//					boolean isfav=params?.isFavorite?!params?.isFavorite.contains("true"):true
//
//					def dvce=Device.findAllByUser(usr)
//					dvce.each{ it.delete(flush:true) }
//					def devic=new Device()
//					log.error params?.dev
//					log.error params?.keyValue
//
//					if(params?.dev && params?.keyValue){
//						devic.keyValue=params?.keyValue
//						devic.description="                 "
//						devic.dev=params?.dev
//						devic.user=usr
//						devic.save(flush:true)
//					}else{
//						devic.keyValue="sin key osea web"
//						devic.description="  "
//						devic.dev=UserDevice.WEB
//						devic.user=usr
//						devic.save(flush:true)
//					}
//
//					if(place1){
//						def o = grails.converters.JSON.parse(place1)
//						def pl = new Place(o)
//						pl.json = place1
//						def cities=EnabledCities.findAllByEnabled(true)
//						boolean isInCityEnabled=false;
//						log.error "asasass"
//						if(pl!=null && pl?.admin1Code){
//
//							log.error "-------1--2--1-------"
//							log.error pl.admin1Code
//							log.error "---------1--1-------"
//							for (EnabledCities cit :cities){
//								if(pl.admin1Code.contains(cit.admin1Code)||cit.admin1Code.contains(pl.admin1Code)){
//
//									log.error "-------1----2---------"
//									log.error pl.streetNumber
//									log.error pl.admin1Code
//									isInCityEnabled=true
//								}
//							}
//
//						}
//
//						if(isInCityEnabled && pl.streetNumber!=null  ){
//							log.debug "esta la ciudad habilitada y tiene una calle correcta"
//							try{
//								log.debug "comenzando a crear el viaje"
//								def oper=null
//								if(usr instanceof CorporateUser){
//									oper=placeCompanyAccountEmployeeService.createFavoriteAndOperation(usr,place1,place2,isfav,piso,dpto,comen,favName,params?.placeinput2,devic.dev)
//
//								}else{
//									oper=placeService.createFavoriteAndOperation(usr,place1,place2,isfav,piso,dpto,comen,favName,params?.placeinput2,devic.dev)
//
//								}
//
//
//								def conf = SpringSecurityUtils.securityConfig
//								if(!usr?.isTestUser){
//									def stringRtaxi=usr?.rtaxi?.companyName?usr?.rtaxi?.companyName:''
//									emailService.send("rrhh@dinerotaxi.com",conf.ui.register.emailFrom, "${stringRtaxi} NUEVO VIAJE,MOBILE ${devic.dev} /n ${usr.firstName} /n ${usr.phone} /n ${usr.email}", usr.email)
//								}
//								//notificationService.notificateOnCreateTrip(oper,usr)
//
//								def tr
//								tr=new com.TripCommand(oper.id,null,oper.createdDate,oper.favorites?.placeFrom.name,
//										oper.favorites?.placeFromPso?oper.favorites?.placeFromPso:'',
//										oper.favorites?.placeFromDto?oper.favorites?.placeFromDto:'',
//										oper.favorites?.placeTo?oper.favorites?.placeTo.name:'',
//										oper.favorites?.comments?oper.favorites?.comments:'',oper.status.toString(),new TaxiCommand())
//								render(contentType: 'text/json',encoding:"UTF-8") {
//									status=100
//									trip=tr
//								}
//							}catch (Exception e){
//								log.error(e.getMessage())
//								log.error(e.getCause())
//								render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//							}
//						}else if( pl.streetNumber==null ){
//							render(contentType: 'text/json',encoding:"UTF-8") { status=9 }
//
//						}else{
//							render(contentType: 'text/json',encoding:"UTF-8") { status=8 }
//						}
//
//					}else{
//						render(contentType: 'text/json',encoding:"UTF-8") { status=126 }
//					}
//				}else{
//					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
//				}
//			}else{
//				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
//			}
//		}catch (Exception e){
//			log.error e.getCause()
//			log.error e.getMessage()
//			render(contentType: 'text/json',encoding:"UTF-8") { status=1}
//		}

	}
	def createTripWithOtherTrip={
		render(contentType: 'text/json',encoding:"UTF-8") { status=11 message="deprecated" }
//		try{
//			def usr=null
//
//			def tok=null
//			if(params?.token){
//				tok=PersistToken.findByToken(params?.token)
//				if(tok){
//					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
//				}
//			}else if (springSecurityService.isLoggedIn()){
//				usr = springSecurityService.currentUser
//
//			}
//			if(tok || springSecurityService.isLoggedIn()){
//				if(usr){
//					def oper1= Operation.get(params?.id)
//					def fav= TemporalFavorites.get(oper1?.favorites?.id)
//					if(fav){
//
//						def cities=EnabledCities.findAllByEnabled(true)
//
//						boolean isInCityEnabled=false;
//						for (EnabledCities cit :cities){
//							if(fav.placeFrom.json.contains(cit.admin1Code)||cit.admin1Code.contains(fav.placeFrom.json)){
//								isInCityEnabled=true
//							}
//						}
//						if(isInCityEnabled && fav.placeFrom.streetNumber!=null  ){
//							def dvce=Device.findAllByUser(usr)
//							dvce.each{ it.delete() }
//
//							def devic=new Device()
//
//							if(params?.dev && params?.keyValue){
//								devic.keyValue=params?.keyValue
//								devic.description=""
//								devic.dev=params?.dev
//								devic.user=usr
//								devic.save(flush:true)
//							}else{
//								devic.keyValue="sin key osea web"
//								devic.description="  "
//								devic.dev=UserDevice.WEB
//								devic.user=usr
//								devic.save(flush:true)
//							}
//							def oper=null
//							if(usr instanceof CorporateUser){
//								oper=new OperationCompanyPending(user:usr,favorites:fav)
//							}else{
//								oper=new OperationPending(user:usr,favorites:fav)
//
//							}
//							oper.isTestUser=usr.isTestUser
//							oper.dev=devic.dev
//							oper.sendToSocket=true
//							if(!oper.save(flush:true)){
//								render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//							}else{
//
//								def trackOperation=new TrackOperation(status:TRANSACTIONSTATUS.PENDING)
//								trackOperation.operation=oper
//								trackOperation.save(flush:true)
//								log.debug "comenzando a crear el viaje"
//								def conf = SpringSecurityUtils.securityConfig
//								if(!usr?.isTestUser){
//									def stringRtaxi=usr?.rtaxi?.companyName?usr?.rtaxi?.companyName:''
//									emailService.send("rrhh@dinerotaxi.com",conf.ui.register.emailFrom, "${stringRtaxi} NUEVO VIAJE,MOBILE ${devic.dev} Usando historial de pedidos /n ${usr.firstName} /n ${usr.phone} /n ${usr.email}", usr.email)
//								}
//								notificationService.notificateOnCreateTrip(oper,usr)
//								def tr
//								tr=new com.TripCommand(oper.id,null,oper.createdDate,oper.favorites?.placeFrom.name,
//										oper.favorites?.placeFromPso?oper.favorites?.placeFromPso:'',
//										oper.favorites?.placeFromDto?oper.favorites?.placeFromDto:'',
//										oper.favorites?.placeTo?oper.favorites?.placeTo.name:'',
//										oper.favorites?.comments?oper.favorites?.comments:'',oper.status.toString(),new TaxiCommand())
//
//								render(contentType: 'text/json',encoding:"UTF-8") {
//									status=100
//									trip=tr
//								}
//							}
//						}else if(fav.placeFrom.streetNumber==null){
//
//							render(contentType: 'text/json',encoding:"UTF-8") { status=9 }
//						}else{
//							render(contentType: 'text/json',encoding:"UTF-8") { status=8 }
//
//						}
//
//					}else{
//						render(contentType: 'text/json',encoding:"UTF-8") { status=126 }
//					}
//				}else{
//					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
//				}
//			}else{
//				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
//			}
//		}catch (Exception e){
//			log.error e.printStackTrace()
//			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
//		}
	}
	def createTripWithFavorite={
		render(contentType: 'text/json',encoding:"UTF-8") { status=11 message="deprecated" }
//		try{
//			def usr=null
//
//			def tok=null
//			if(params?.token){
//				tok=PersistToken.findByToken(params?.token)
//				if(tok){
//					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
//				}
//			}else if (springSecurityService.isLoggedIn()){
//				usr = springSecurityService.currentUser
//
//			}
//			if(tok || springSecurityService.isLoggedIn()){
//				if(usr){
//					def fav= Favorites.get(params?.favoriteId)
//					if(fav){
//
//						def cities=EnabledCities.findAllByEnabled(true)
//
//						boolean isInCityEnabled=false;
//						for (EnabledCities cit :cities){
//							if(fav.placeFrom.json.contains(cit.admin1Code)){
//								isInCityEnabled=true
//							}
//						}
//						if(isInCityEnabled && fav.placeFrom.streetNumber!=null  ){
//							def dvce=Device.findAllByUser(usr)
//							dvce.each{ it.delete() }
//
//							def devic=new Device()
//
//							if(params?.dev && params?.keyValue){
//								devic.keyValue=params?.keyValue
//								devic.description=""
//								devic.dev=params?.dev
//								devic.user=usr
//								devic.save(flush:true)
//							}else{
//								devic.keyValue="sin key osea web"
//								devic.description="  "
//								devic.dev=UserDevice.WEB
//								devic.user=usr
//								devic.save(flush:true)
//							}
//
//							def oper=null
//							if(usr instanceof CorporateUser){
//								oper=new OperationCompanyPending(user:usr,favorites:fav)
//							}else{
//								oper=new OperationPending(user:usr,favorites:fav)
//
//							}
//							oper.isTestUser=usr.isTestUser
//							oper.dev=devic.dev
//							
//							oper.sendToSocket=true
//
//
//							if(!oper.save(flush:true)){
//								render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
//							}else{
//
//								def trackOperation=new TrackOperation(status:TRANSACTIONSTATUS.PENDINGFAVORITETRIP)
//								trackOperation.operation=oper
//								trackOperation.save(flush:true)
//								//						 	notificationService.notificateOnCreateTrip(oper,usr)
//								render(contentType: 'text/json',encoding:"UTF-8") {
//									id=oper.id
//									status=100
//								}
//							}
//						}else if(fav.placeFrom.streetNumber==null){
//
//							render(contentType: 'text/json',encoding:"UTF-8") { status=9 }
//						}else{
//							render(contentType: 'text/json',encoding:"UTF-8") { status=8 }
//
//						}
//
//					}else{
//						render(contentType: 'text/json',encoding:"UTF-8") { status=126 }
//					}
//				}else{
//					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
//				}
//			}else{
//				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
//			}
//		}catch (Exception e){
//			log.error e.printStackTrace()
//			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
//		}
	}
	def cancelTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def id=params?.id
					def operationInstance = Operation.get(params.id)
					if(operationInstance){
						if (operationInstance.user==usr){

							try {
								//operationInstance.delete(flush: true)
								//								notificationService.notificateOnCancelTripByUser(operationInstance,usr)
								operationInstance.status=TRANSACTIONSTATUS.CANCELED
								operationInstance.save(flush:true)

								if(usr instanceof CorporateUser){
									Operation.executeUpdate("update Operation b set b.class=:cClass, b.status=:status where b.id=:oldTitle",
											[cClass: OperationCompanyHistory.name, oldTitle: operationInstance.id,status:TRANSACTIONSTATUS.CANCELED])

								}else{
									Operation.executeUpdate("update Operation b set b.class=:cClass, b.status=:status where b.id=:oldTitle",
											[cClass: OperationHistory.name, oldTitle: operationInstance.id,status:TRANSACTIONSTATUS.CANCELED])



								}

								def trackOperation=new TrackOperation(status:TRANSACTIONSTATUS.CANCELED)
								trackOperation.operation=operationInstance
								trackOperation.save(flush:true)
								notificationService.notificateOnCancelTripByUserForRadioTaxi(operationInstance,usr)
								render(contentType: 'text/json',encoding:"UTF-8") {
									opId=operationInstance.id.toString()
									status=100
								}
							}
							catch (org.springframework.dao.DataIntegrityViolationException e) {
								render(contentType: 'text/json',encoding:"UTF-8") { status=123 }
							}

						}else{
							render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
						}
					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=121 }
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def geocode={
		try{
			if(params?.place){
				log.error params as JSON
				def source=geocoderService.queryForJSONUTF(params?.place)
				log.error "-------------------"
				log.error  source
				log.error "-------------------"
				render(contentType: 'text/json',encoding:"UTF-8") {
					status=100
					place=source
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=16 }

			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=404 }
		}
	}
	def geocodeUtf={
		try{
			if(params?.place){
				def source=geocoderService.queryForJSONUTF(params?.place)
				render(contentType: 'application/json',encoding:"UTF-8") {
					status=100
					place=source
				}

			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=16 }

			}

		}catch (Exception e){
			log.error e.getMessage()
			log.error e.getCause()
			render(contentType: 'text/json',encoding:"UTF-8") { status=404 }
		}
	}
	def geocodes={
		if(params?.place){
			def source=geocoderService.queryForJSONUTF(params?.place)

			render(contentType: 'text/json',encoding:"UTF-8") {
				status=100
				place=source
			}

		}else{
			render(contentType: 'text/json',encoding:"UTF-8") { status=16 }

		}

	}
	def confirmTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def id=params?.id
					def operationInstance = Operation.get(params.id)
					if(operationInstance){
						if (operationInstance.user==usr){
							if (operationInstance?.taxista) {
								try {
									//operationInstance.delete(flush: true)
									//										notificationService.notificateOnCancelTripByUser(operationInstance,usr)
									if(usr instanceof CorporateUser){
										Operation.executeUpdate("update Operation b set b.class=:cClass, b.status=:status where b.id=:oldTitle",
												[cClass: OperationCompanyHistory.name, oldTitle: operationInstance.id,status:TRANSACTIONSTATUS.CANCELED])

									}else{
										Operation.executeUpdate("update Operation b set b.class=:cClass, b.status=:status where b.id=:oldTitle",
												[cClass: OperationHistory.name, oldTitle: operationInstance.id,status:TRANSACTIONSTATUS.CANCELED])


									}

									render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
								}
								catch (org.springframework.dao.DataIntegrityViolationException e) {
									render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
								}
							}else{
								render(contentType: 'text/json',encoding:"UTF-8") { status=114 }
							}

						}else{
							render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
						}
					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=121 }
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def deleteListTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					if(params?.id){
						def id=params?.id.split(",")
						id.each{
							def operationInstance = Operation.get(it)
							if(operationInstance){
								if (operationInstance.user==usr){
									operationInstance.setEnabled(false);
									operationInstance.save(flush:true);
								}
							}
						}
						render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def deleteTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def id=params?.id
					def operationInstance = Operation.get(params.id)
					if(operationInstance){
						if (operationInstance.user==usr){
							operationInstance.setEnabled(false);
							operationInstance.save(flush:true);
							render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
						}else{
							render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
						}
					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=121 }
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def deleteAllTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c =null
					if(usr instanceof CorporateUser){
						c = OperationCompanyHistory.createCriteria()
					}else{
						c = OperationHistory.createCriteria()
					}
					def opL=c.list([offset:params.offset,max:params.max]){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
					}
					opL.each {
						it.setEnabled(false);
						it.save(flush:true);
					}
					render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getListTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c = Operation.createCriteria()
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
					}
					def ids= opL.collect { it.id }
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						id=ids.join(",")

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getHistoryTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyHistory.createCriteria()
					}else{
						c= OperationHistory.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
					}
					def ids= opL.collect { it.id }
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						id=ids.join(",")

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getFullPendingTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyPending.createCriteria()
					}else{
						c= OperationPending.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
						order("createdDate", "desc")
					}
					def listTrip=new ArrayList()
					opL.each{
						def res=new TaxiCommand()
						if(it?.intermediario){
							if(it?.taxista){
								res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
								def v=Vehicle.findByTaxistas(it.taxista)
								if(v && v.patente.equals("------")){
									res.marca=v.marca
									res.patente=v.patente
									res.modelo=v.modelo
								}else{
									res.marca=""
									res.patente=""
									res.modelo=""
								}
								res.numeroMovil=it.taxista.email.split("@")[0]
							}
							def com=Company.get(it.intermediario.employee.id)
							if(com){
								res.empresa= com.companyName
								res.companyPhone=com.phone
							}
						}else if (it?.taxista){
							res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
							def v=Vehicle.findByTaxistas(it.taxista)
							if(v && v.patente.equals("------")){
								res.marca=v.marca
								res.patente=v.patente
								res.modelo=v.modelo
							}else{
								res.marca=""
								res.patente=""
								res.modelo=""
							}
						}
						def tr=new com.TripCommand(it.id,it.timeTravel,it.createdDate,it.favorites?.placeFrom.street+" "+ it.favorites?.placeFrom.streetNumber,
								it.favorites?.placeFromPso?it.favorites?.placeFromPso:'',
								it.favorites?.placeFromDto?it.favorites?.placeFromDto:'',
								it.favorites?.placeTo?it.favorites?.placeTo.street+" "+ it.favorites?.placeTo.streetNumber:'',
								it.favorites?.comments?it.favorites?.comments:'',it.status.toString(),res,it.favorites instanceof Favorites?it.favorites?.name:"")
						listTrip.add(tr)
					}
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						json=listTrip

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getFullHistoryTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyHistory.createCriteria()
					}else{
						c= OperationHistory.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
						order("createdDate", "desc")
					}
					def listTrip=new ArrayList()
					opL.each{
						def res=new TaxiCommand()
						if(it?.intermediario){
							if(it?.taxista){
								res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
								def v=Vehicle.findByTaxistas(it.taxista)
								if(v){
									res.marca=v.marca
									res.patente=v.patente
									res.modelo=v.modelo
								}
								res.numeroMovil=it.taxista.email.split("@")[0]
							}
							def com=Company.get(it.intermediario.employee.id)
							if(com){
								res.empresa= com.companyName
							}
						}else if (it?.taxista){
							res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
							def v=Vehicle.findByTaxistas(it.taxista)
							if(v){
								res.marca=v.marca
								res.patente=v.patente
								res.modelo=v.modelo
							}
						}
						def tr=new com.TripCommand(it.id,it.timeTravel,it.createdDate,it.favorites?.placeFrom.street+" "+ it.favorites?.placeFrom.streetNumber,
								it.favorites?.placeFromPso?it.favorites?.placeFromPso:'',
								it.favorites?.placeFromDto?it.favorites?.placeFromDto:'',
								it.favorites?.placeTo?it.favorites?.placeTo.street+" "+ it.favorites?.placeTo.streetNumber:'',
								it.favorites?.comments?it.favorites?.comments:'',it.status.toString(),res,it.favorites instanceof Favorites?it.favorites?.name:"")
						listTrip.add(tr)
					}
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						json=listTrip

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	
	def getListTrips={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){

					def critereaPending=null
					if(usr instanceof CorporateUser){
						critereaPending= OperationCompanyPending.createCriteria()
					}else{
						critereaPending= OperationPending.createCriteria()

					}
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyHistory.createCriteria()
					}else{
						c= OperationHistory.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
							isNotNull('createdDate')
						}
						order("createdDate", "desc")
						maxResults(10)
					}
					def historyTrips=new ArrayList()
					opL.each{ operation->
						def jsonBuilder = new groovy.json.JsonBuilder()
						def res=new TaxiCommand()
						def vehicle=null
						def company=null
						if(it?.taxista){
							vehicle=Vehicle.findByTaxistas(it.taxista)
						}
						if(it?.intermediario){
						    company=Company.get(it.intermediario.employee.id)
						}
						boolean cc =operation?.user instanceof CorporateUser
						def movil=it?.taxista?.email?it.taxista.email.split("@")[0]:''
						def name=it?.taxista?.firstName?it.taxista.firstName+' '+it?.taxista?.lastName:''
						def driverBuilder = jsonBuilder{
							brandName vehicle?.marca?:''
							carLicense vehicle?.patente?:''
							vehicleModel vehicle?.modelo?:''
							companyName  company?.companyName?:''
							companyPhone company?.phone?:''
							driverNumber movil
							driverName name
						}
						def placeFromBuilder = jsonBuilder{
							street  operation?.favorites?.placeFrom?.street
							streetNumber  operation?.favorites?.placeFrom?.streetNumber
							country  operation?.favorites?.placeFrom?.country
							locality  operation?.favorites?.placeFrom?.locality
							admin1Code  operation?.favorites?.placeFrom?.admin1Code
							locality  operation?.favorites?.placeFrom?.locality
							floor 	operation?.favorites?.placeFromPso
							lat		operation?.favorites?.placeFrom?.lat
							lng		operation?.favorites?.placeFrom?.lng
							appartment  operation?.favorites?.placeFromDto
						}
				
						def placeToBuilder = jsonBuilder{
							street  operation?.favorites?.placeTo?.street?:''
							streetNumber  operation?.favorites?.placeTo?.streetNumber?:''
							country  operation?.favorites?.placeTo?.country?:''
							locality  operation?.favorites?.placeTo?.locality?:''
							admin1Code  operation?.favorites?.placeTo?.admin1Code?:''
							locality  operation?.favorites?.placeTo?.locality?:''
							floor	operation?.favorites?.placeToFloor?:''
							lat		operation?.favorites?.placeTo?.lat
							lng		operation?.favorites?.placeTo?.lng
							appartment operation?.favorites?.placeToApartment?:''
						}
				
						def optionsBuilder = jsonBuilder{
							messaging operation?.options?.messaging
							pet operation?.options?.pet
							airConditioning operation?.options?.airConditioning
							smoker operation?.options?.smoker
							specialAssistant operation?.options?.specialAssistant
							luggage operation?.options?.luggage
						}
						
						def operationBuilder = jsonBuilder{
							id operation.id
							status operation.status.toString()
							driver_number operation?.driverNumber?:null
							payment_reference operation?.paymentReference?:''
							createdDate operation.createdDate
							placeFrom placeFromBuilder
							placeTo placeToBuilder
							driver driverBuilder
							options optionsBuilder
							comments operation?.favorites?.comments?:""
							device operation?.dev?operation?.dev.toString():"UNDEFINED"
						}
						
						historyTrips.add(operationBuilder)
					}
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						history=historyTrips
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getFullTrips={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){

					def critereaPending=null
					if(usr instanceof CorporateUser){
						critereaPending= OperationCompanyPending.createCriteria()
					}else{
						critereaPending= OperationPending.createCriteria()

					}
					def operationPendigsLis=critereaPending.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
							isNotNull('createdDate')
						}
						order("createdDate", "desc")
					}
					def pendingTrips=new ArrayList()
					operationPendigsLis.each{
						def res=new TaxiCommand()
						if(it?.intermediario){
							if(it?.taxista){
								res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
								def v=Vehicle.findByTaxistas(it.taxista)
								if(v && v.patente.equals("------")){
									res.marca=v.marca
									res.patente=v.patente
									res.modelo=v.modelo
								}else{
									res.marca=""
									res.patente=""
									res.modelo=""
								}
								res.numeroMovil=it.taxista.email.split("@")[0]
							}
							def com=Company.get(it.intermediario.employee.id)
							if(com){
								res.empresa= com.companyName
								res.companyPhone=com.phone
							}
						}else if (it?.taxista){
							res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
							def v=Vehicle.findByTaxistas(it.taxista)
							if(v && v.patente.equals("------")){
								res.marca=v.marca
								res.patente=v.patente
								res.modelo=v.modelo
							}else{
								res.marca=""
								res.patente=""
								res.modelo=""
							}
						}
						def tr=new com.TripCommand(it.id,it.timeTravel,it.createdDate,it.favorites?.placeFrom.name,
								it.favorites?.placeFromPso?it.favorites?.placeFromPso:'',
								it.favorites?.placeFromDto?it.favorites?.placeFromDto:'',
								it.favorites?.placeTo?it.favorites?.placeTo.name:'',
								it.favorites?.comments?it.favorites?.comments:'',it.status.toString(),res,it.favorites instanceof Favorites?it.favorites?.name:"")
						pendingTrips.add(tr)
					}
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyHistory.createCriteria()
					}else{
						c= OperationHistory.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
							isNotNull('createdDate')
						}
						order("createdDate", "desc")
						maxResults(10)
					}
					def historyTrips=new ArrayList()
					opL.each{
						def res=new TaxiCommand()
						if(it?.intermediario){
							if(it?.taxista){
								res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
								def v=Vehicle.findByTaxistas(it.taxista)
								if(v){
									res.marca=v.marca
									res.patente=v.patente
									res.modelo=v.modelo
								}
								res.numeroMovil=it.taxista.email.split("@")[0]
							}
							def com=Company.get(it.intermediario.employee.id)
							if(com){
								res.empresa= com.companyName
								res.companyPhone=com.phone
							}
						}else if (it?.taxista){
							res.nombre= it.taxista.firstName+" "+ it.taxista.lastName
							def v=Vehicle.findByTaxistas(it.taxista)
							if(v){
								res.marca=v.marca
								res.patente=v.patente
								res.modelo=v.modelo
							}
						}
						def tr=new com.TripCommand(it.id,it.timeTravel,it.createdDate,it.favorites?.placeFrom.name,
								it.favorites?.placeFromPso?it.favorites?.placeFromPso:'',
								it.favorites?.placeFromDto?it.favorites?.placeFromDto:'',
								it.favorites?.placeTo?it.favorites?.placeTo.name:'',
								it.favorites?.comments?it.favorites?.comments:'',it.status.toString(),res,it.favorites instanceof Favorites?it.favorites?.name:"")
						historyTrips.add(tr)
					}
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						history=historyTrips
						pending=pendingTrips

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}

		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getPendingTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def c=null
					if(usr instanceof CorporateUser){
						c= OperationCompanyPending.createCriteria()
					}else{
						c= OperationPending.createCriteria()

					}
					def opL=c.list(){
						and{
							eq ('user',usr)
							eq('enabled',true)
						}
					}
					def ids= opL.collect { it.id }
					render(contentType: 'text/json',encoding:"UTF-8") {
						status=100
						id=ids.join(",")

					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def id=params?.id
					def operationInstance = Operation.get(params.id)
					if(operationInstance){
						if (operationInstance.user==usr){
							render(contentType: 'text/json',encoding:"UTF-8") {
								status=100
								opid=operationInstance.id
								creationDate=operationInstance.createdDate
								placeFrom=operationInstance.favorites?.placeFrom.street+" "+ operationInstance.favorites?.placeFrom.streetNumber
								piso=operationInstance.favorites?.placeFromPso?operationInstance.favorites?.placeFromPso:''
								depto=operationInstance.favorites?.placeFromDto?operationInstance.favorites?.placeFromDto:''
								placeTo=operationInstance.favorites?.placeTo?operationInstance.favorites?.placeTo.street+" "+ operationInstance.favorites?.placeTo.streetNumber:''
								comments=operationInstance.favorites?.comments?operationInstance.favorites?.comments:''
								status=operationInstance.status
							}
						}else{
							render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
						}
					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=121 }
					}
				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def calificateTrip={
		try{
			def usr=null

			def tok=null
			if(params?.token){
				tok=PersistToken.findByToken(params?.token)
				if(tok){
					usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
				}
			}else if (springSecurityService.isLoggedIn()){
				usr = springSecurityService.currentUser

			}
			if(tok || springSecurityService.isLoggedIn()){
				if(usr){
					def id=params?.id
					def comen=params?.comments?:""
					if(params?.stars){
						def calif=Integer.valueOf(params?.stars)
						if(calif<=5 && calif >0){

							def operationInstance=null
							if(usr instanceof CorporateUser){
								operationInstance= OperationCompanyHistory.get(params.id)
							}else{
								operationInstance= OperationHistory.get(params.id)

							}
							if(operationInstance){
								if (operationInstance.user==usr){
									def hasCalif=Calification.findAllByOperation(operationInstance)
									if(!hasCalif){
										new Calification(stars:calif,operation:operationInstance,taxista:operationInstance.taxista,comments:comen).save(flush:true)
										operationInstance.status=TRANSACTIONSTATUS.CALIFICATED
										operationInstance.save(flush:true)

										def trackOperation=new TrackOperation(status:TRANSACTIONSTATUS.CALIFICATED)
										trackOperation.operation=operationInstance
										trackOperation.save(flush:true)
										//									notificationService.notificateOnTripCalificate(operationInstance,operationInstance.user,false)
										render(contentType: 'text/json',encoding:"UTF-8") { status=100 }
									}else{
										render(contentType: 'text/json',encoding:"UTF-8") { status=129 }
									}
								}else{
									render(contentType: 'text/json',encoding:"UTF-8") { status=125 }
								}
							}else{
								render(contentType: 'text/json',encoding:"UTF-8") { status=121 }
							}
						}else{
							render(contentType: 'text/json',encoding:"UTF-8") { status=128 }
						}

					}else{
						render(contentType: 'text/json',encoding:"UTF-8") { status=127 }
					}

				}else{
					render(contentType: 'text/json',encoding:"UTF-8") { status=2 }
				}
			}else{
				render(contentType: 'text/json',encoding:"UTF-8") { status=1 }
			}
		}catch (Exception e){
			log.error e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=11 }
		}
	}
	def getOperationHistory ={
		def tok=PersistToken.findByToken(params.token)
		if(!tok){
			render(contentType: 'text/json',encoding:"UTF-8") { status=411 }
			return false
		}
		def usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
		if(!usr){
			render(contentType: 'text/json',encoding:"UTF-8") { status=412 }
			return false
		}
		def sortIndex   = params.sidx ?: 'id'
		def sortOrder   = params.sord ?: 'desc'
		def maxRows     = params?.rows? Integer.valueOf(params.rows) : 10
		def currentPage = params?.page? Integer.valueOf(params.page) : 1
		
		def rowOffset     = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
		def customers = null
		print "operationHistory"
		if(usr instanceof RealUser){
			customers = OperationHistory.createCriteria().list(max:maxRows, offset:rowOffset) {
				eq('user', usr)
				order(sortIndex, sortOrder)
			}
		}else{
			customers = OperationCompanyHistory.createCriteria().list(max:maxRows, offset:rowOffset) {
				eq('user', usr)
				order(sortIndex, sortOrder)
			}
			
		}
		print "er"
		def totalRows = customers.totalCount
		def numberOfPages = Math.ceil(totalRows / maxRows)
		
		def jsonCells = customers.collect {
			generateOperationJson(it)
		}
		
		def jsonData= [rows: jsonCells,page:currentPage,records:totalRows,total:numberOfPages,status:100]
		render jsonData as JSON
	}
	def getOperationScheduled ={
		try {
			def tok=PersistToken.findByToken(params.token)
			if(!tok){
				render(contentType: 'text/json',encoding:"UTF-8") { status=411 }
				return false
			}
			def usr=User.findByUsernameAndRtaxi(tok.username,tok?.rtaxi?User.get(tok.rtaxi):null)
			if(!usr){
				render(contentType: 'text/json',encoding:"UTF-8") { status=412 }
				return false
			}
			def sortIndex   = params.sidx ?: 'id'
			def sortOrder   = params.sord ?: 'desc'
			def maxRows     = params?.rows? Integer.valueOf(params.rows) : 10
			def currentPage = params?.page? Integer.valueOf(params.page) : 1
			
			def rowOffset     = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
			
			def usersCriterea = CorporateUser.createCriteria()
			
			def customers = DelayOperation.createCriteria().list(max:maxRows, offset:rowOffset) {
				eq('user', usr)
				order(sortIndex, sortOrder)
			}
			def totalRows = customers.totalCount
			def numberOfPages = Math.ceil(totalRows / maxRows)
			print customers
			def jsonCells = customers.collect {
				def pojo = generateOperationJson(it)
				pojo.executionTime = utilsApiService.generateFormatedAddressToClient(it?.executionTime,it?.user)
				return pojo
			}
			def jsonData= [rows: jsonCells,page:currentPage,records:totalRows,total:numberOfPages,status:100]
			render jsonData as JSON
		} catch (Exception e) {
			e.printStackTrace()
			render(contentType: 'text/json',encoding:"UTF-8") { status=200 }
		}
		
		
	}
	
	def generateOperationJson(operation){
		def jsonBuilder = new groovy.json.JsonBuilder()
		boolean cc =operation?.user instanceof CorporateUser
		def userBuilder = jsonBuilder{
			id  operation?.user?.id
			firstName operation?.user?.firstName
			lastName operation?.user?.lastName
			companyName operation?.user?.companyName
			phone operation?.user?.phone
			rtaxi operation?.user?.rtaxi?.id
			lang operation?.user?.rtaxi?.lang?:'es'
			//city operation?.user?.city
			cityName operation?.user?.city?.name
			cityCode operation?.user?.city?.admin1Code
			isFrequent operation?.user?.isFrequent
			isCC cc
		}
		def placeFromBuilder = jsonBuilder{
			street  operation?.favorites?.placeFrom?.street
			streetNumber  operation?.favorites?.placeFrom?.streetNumber
			country  operation?.favorites?.placeFrom?.country
			locality  operation?.favorites?.placeFrom?.locality
			admin1Code  operation?.favorites?.placeFrom?.admin1Code
			locality  operation?.favorites?.placeFrom?.locality
			floor 	operation?.favorites?.placeFromPso
			lat		operation?.favorites?.placeFrom?.lat
			lng		operation?.favorites?.placeFrom?.lng
			appartment  operation?.favorites?.placeFromDto
		}

		def placeToBuilder = jsonBuilder{
			street  operation?.favorites?.placeTo?.street?:''
			streetNumber  operation?.favorites?.placeTo?.streetNumber?:''
			country  operation?.favorites?.placeTo?.country?:''
			locality  operation?.favorites?.placeTo?.locality?:''
			admin1Code  operation?.favorites?.placeTo?.admin1Code?:''
			locality  operation?.favorites?.placeTo?.locality?:''
			floor	operation?.favorites?.placeToFloor?:''
			lat		operation?.favorites?.placeTo?.lat?:0
			lng		operation?.favorites?.placeTo?.lng?:0
			appartment operation?.favorites?.placeToApartment?:''
		}

		def optionsBuilder = jsonBuilder{
			messaging operation?.options?.messaging
			pet operation?.options?.pet
			airConditioning operation?.options?.airConditioning
			smoker operation?.options?.smoker
			specialAssistant operation?.options?.specialAssistant
			luggage operation?.options?.luggage
			airport operation?.options?.airport
			vip operation?.options?.vip
			invoice operation?.options?.invoice
		}
		def operationBuilder = jsonBuilder{
			id operation.id
			status operation.status.toString()
			driver_number operation?.driverNumber?:null
			payment_reference operation?.paymentReference?:''
			lat operation?.favorites?.placeFrom?.lat
			lng operation?.favorites?.placeFrom?.lng
			placeFrom placeFromBuilder
			placeTo placeToBuilder
			user userBuilder
			options optionsBuilder
			comments operation?.favorites?.comments?:""
			device operation?.dev?operation?.dev.toString():"UNDEFINED"
			amount operation?.amount
			createdDate utilsApiService.generateFormatedAddressToClient(operation?.createdDate,operation?.user)
		}
		return operationBuilder

	}
}

