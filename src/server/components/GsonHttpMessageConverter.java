package server.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import static util.IOUtils.convertStreamToString;

/**
 * Inspired by http://stackoverflow.com/questions/3754055/spring-mvc-mapping-view-for-google-gson
 * @author Steven Weston
 */
@Component
public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private GsonBuilder gsonBuilder = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public GsonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return MediaType.APPLICATION_JSON.isCompatibleWith(mediaType);
	}

	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return MediaType.APPLICATION_JSON.isCompatibleWith(mediaType);
	}

	public void registerTypeAdapter(Type type, Object serializer) {
		gsonBuilder.registerTypeAdapter(type, serializer);
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		try {
			Gson gson = gsonBuilder.create();
			return gson.fromJson(convertStreamToString(inputMessage.getBody()), clazz);
		} catch (JsonParseException e) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
		}
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		Type genericType = TypeToken.get(o.getClass()).getType();

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputMessage.getBody(), DEFAULT_CHARSET));
		try {
			Gson gson = gsonBuilder.create();
			writer.append(gson.toJson(o, genericType));
		} finally {
			writer.flush();
			writer.close();
		}
	}
}
