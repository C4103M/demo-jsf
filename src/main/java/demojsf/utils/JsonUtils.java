package demojsf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import demojsf.model.Aluno;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JsonUtils {


	    public static final String ALUNO_API = "http://localhost:8082/demo-api/alunos";
	    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();  

	    public static List<Aluno> toList(InputStream inputStream) throws IOException {  
	        try {  
	            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {});  
	        }  
	        catch (JacksonException exc) {  
	            throw new IOException(exc);  
	        }  
	    }  

	    public static Aluno toObject(InputStream inputStream) throws IOException {  
	        try {  
	            return OBJECT_MAPPER.readValue(inputStream, Aluno.class);  
	        }  
	        catch (JacksonException exc) {  
	            throw new IOException(exc);  
	        }  
	    }  

	    public static String toJson(Aluno a) throws IOException {  
	        try {  
	            return OBJECT_MAPPER.writeValueAsString(a);  
	        }  
	        catch (JacksonException exc) {  
	            throw new IOException(exc);  
	        }
	    }

	    public static Aluno buildAluno() {  	        

	       Aluno a = new Aluno();
	       a.setEmail("mura@arum.com");
	       a.setNome("Murakami");
	         
	       return a;
	    }

	}

