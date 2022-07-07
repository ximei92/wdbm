"use strict"

/**
 * 문자열이 빈문자열인지 체크
 * 
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str) {

	if (typeof str == "undefined" || str == null || str == "")
		return true;
	else
		return false;

}