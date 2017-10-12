package ar.com.goliath
import java.util.Set;

import ar.com.imported.*
import ar.com.operation.Options
class EmployUser extends User implements Serializable {
   Date createdDate
   Date lastModifiedDate
   User employee
   UStatus status=UStatus.WITHOUTFINISHREGISTRATION
   TypeEmployer typeEmploy
   boolean agree
   boolean politics
   boolean visible=true
   Boolean admin = false
   String cuit
   String licenceNumber
   Date licenceEndDate
	boolean isCorporate=true
	boolean isVip=true
	boolean isRegular=true
   
	def beforeInsert = {
		createdDate = new Date()
	}
	def beforeUpdate = {
		lastModifiedDate = new Date()
	}
	static hasMany = [profileVariables: ProfileBuilder]
	
  	
	static constraints = {
		isCorporate defaultValue: true
		isVip defaultValue: true
		isRegular defaultValue: true
		licenceNumber nullable:true,blank:true
		licenceEndDate nullable:true,blank:true
		cuit nullable:true,blank:true
		username blank: false, unique: true
		password blank: false
		email blank: false,nullable:false,email:true
		firstName nullable:true,blank:true
		lastName nullable:true,blank:true
		phone nullable:false,blank:false
		phone1 nullable:true,blank:true
		createdDate nullable:true,blank:true
		lastModifiedDate nullable:true,blank:true
		employee nullable:true,blank:true
		latitude(nullable:true,blank:true)
		longitude(nullable:true,blank:true)
	}
}

public enum TypeEmployer{
	TAXISTA,OPERADOR,CORDINADOR,TELEFONISTA,COMPANYEMPLOYEE,MONITOR
	//DRIVER,OPERATOR,SUPERVISOR,TELEPHONIST,COMPANYEMPLOYEE
}
