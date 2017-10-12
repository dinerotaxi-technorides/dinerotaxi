package ar.com.goliath.corporate.billing

import ar.com.operation.Operation

class BillingPayments {
	Double bankCharges
	Double amount
	Boolean taxDeducted
	
	Date paymentDate
	String paymentMode
	String reference
	String notes
	Boolean sendEmail
	static constraints = {
		bankCharges     nullable:true,blank:true
		amount     nullable:false,blank:false
		taxDeducted nullable:true,blank:true
		paymentDate   nullable:true,blank:true
		paymentMode     nullable:false,blank:false
		reference  nullable:true,blank:true
		notes  nullable:true,blank:true
		sendEmail  nullable:false,blank:false
		
	}
}