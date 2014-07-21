package model.player;

import model.role.Role;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * @author Steven Weston
 */
public class RoleListSerializer extends JsonSerializer<List<Role<?, ?>>> {

	@Override
	public void serialize(
			List<Role<?, ?>> roles, JsonGenerator jgen, SerializerProvider provider
	) throws IOException, JsonProcessingException {

		jgen.writeStartArray();
		for (Role<?, ?> role : roles) {
			jgen.writeString(role.getName());
		}
		jgen.writeEndArray();
	}
}
