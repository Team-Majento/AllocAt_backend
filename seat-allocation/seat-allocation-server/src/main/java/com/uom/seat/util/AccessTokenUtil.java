package com.uom.seat.util;

/**
 * @author rangalal
 *
 */
public final class AccessTokenUtil {

	private AccessTokenUtil() {
	}

	/**
	 * @param authorizationHeader
	 * @return
	 */
	public static String getBearerToken(final String authorizationHeader) {

		if (!authorizationHeader.isEmpty() && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7, authorizationHeader.length());
		} else {
			return null;
		}

	}

}
