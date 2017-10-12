package ar.com.notification;



public enum StatusCode {
	OK(200), FORMAT_ERROR(600), INVALID_FIELDS_FORMAT(601), INVALID_APP(300), INVALID_DEVICE_ID(
			301), NOTIFICATION_FAILED(400), INEXISTANT_CODE_DEVICE(302), MAIL_NOTIFICATION_FAILED(
			403), ANDROID_AUTH_TOKEN_FAILED(503), INTERNAL_ERROR(501), GOOGLE_SERVER_ERROR(
			502), IPHONE_NOTIFICATION_FAILED(404), GOOGLE_SERVER_UNPARSABLE_RESPONSE(
			504);

	private int num;

	StatusCode(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

}