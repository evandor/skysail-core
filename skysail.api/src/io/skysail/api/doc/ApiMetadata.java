package io.skysail.api.doc;

import java.lang.reflect.Method;


public class ApiMetadata {

	private static String summaryForGet;
	private static String summaryForPost;
	private static String summaryForPut;
	private static String summaryForDelete;

	private static String descriptionForGet;
	private static String descriptionForPost;
	private static String descriptionForPut;
	private static String descriptionForDelete;

	private static String[] tagsForGet;
	private static String[] tagsForPost;
	private static String[] tagsForPut;
	private static String[] tagsForDelete;

	public static class ApiMetadataBuilder {

		// === get ===========================================

		public ApiMetadataBuilder summaryForGet(Class<?> cls, String methodName) {
			summaryForGet = getValueOrNullForApiSummary(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder descriptionForGet(Class<?> cls, String methodName) {
			descriptionForGet = getValueOrNullForApiDescription(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder tagsForGet(Class<?> cls, String methodName) {
			tagsForGet = getValueOrEmptyForApiTags(getMethod(cls, methodName));
			return this;
		}

		// === post ===========================================

		public ApiMetadataBuilder summaryForPost(Class<?> cls, String methodName) {
			summaryForPost = getValueOrNullForApiSummary(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder descriptionForPost(Class<?> cls, String methodName) {
			descriptionForPost = getValueOrNullForApiDescription(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder tagsForPost(Class<?> cls, String methodName) {
			tagsForPost = getValueOrEmptyForApiTags(getMethod(cls, methodName));
			return this;
		}

		// === put ===========================================

		public ApiMetadataBuilder summaryForPut(Class<?> cls, String methodName) {
			summaryForPut = getValueOrNullForApiSummary(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder descriptionForPut(Class<?> cls, String methodName) {
			descriptionForPut = getValueOrNullForApiDescription(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder tagsForPut(Class<?> cls, String methodName) {
			tagsForPut = getValueOrEmptyForApiTags(getMethod(cls, methodName));
			return this;
		}

		// === delete ===========================================

		public ApiMetadataBuilder summaryForDelete(Class<?> cls, String methodName) {
			summaryForDelete = getValueOrNullForApiSummary(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder descriptionForDelete(Class<?> cls, String methodName) {
			descriptionForDelete = getValueOrNullForApiDescription(getMethod(cls, methodName));
			return this;
		}

		public ApiMetadataBuilder tagsForDelete(Class<?> cls, String methodName) {
			tagsForDelete = getValueOrEmptyForApiTags(getMethod(cls, methodName));
			return this;
		}

		private String getValueOrNullForApiSummary(Method method) {
			if (method != null && method.getDeclaredAnnotation(ApiSummary.class) != null) {
				return method.getDeclaredAnnotation(ApiSummary.class).value();
			}
			return null;
		}

		private String getValueOrNullForApiDescription(Method method) {
			if (method != null && method.getDeclaredAnnotation(ApiDescription.class) != null) {
				return method.getDeclaredAnnotation(ApiDescription.class).value();
			}
			return null;
		}

		private String[] getValueOrEmptyForApiTags(Method method) {
			if (method != null && method.getDeclaredAnnotation(ApiTags.class) != null) {
				return method.getDeclaredAnnotation(ApiTags.class).value();
			}
			return new String[0];
		}

		private Method getMethod(Class<?> cls, String methodName) {
			try {
				return cls.getDeclaredMethod(methodName);
			} catch (NoSuchMethodException | SecurityException e) { // NOSONAR
				//log.warn(e.getMessage());
			}
			return null;
		}
	}

}
