package demojsf.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import demojsf.model.Aluno;
import demojsf.model.AlunoMapper;
import demojsf.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
public class AlunoService implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Aluno> buscarTodos() {
		HttpClient httpClient = HttpClient.newHttpClient();  
	    HttpRequest request = HttpRequest.newBuilder(URI.create(JsonUtils.ALUNO_API)).GET().build();  

	    try {  
	        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());  

	        int statusCode = response.statusCode();  
	        log.info("HTTP status: " + statusCode);  

	        log.info("Users returned in request: " + response.body());
	        return AlunoMapper.toList(response.body());
	       
	    }  
	    catch (IOException | InterruptedException e) {  
	        throw new RuntimeException(e);  
	    }  
	}

	public void salvar(Aluno aluno) throws IOException {

		HttpClient httpClient = HttpClient.newHttpClient();
		String jsonBody = JsonUtils.toJson(aluno);
		log.info("\n\nJSON enviado no POST: " + jsonBody + "\n\n");
		try {

		HttpRequest request = HttpRequest.newBuilder(URI.create(JsonUtils.ALUNO_API))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			log.info("HTTP status POST: " + response.statusCode());
			log.info("Response body: " + response.body());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				throw new RuntimeException("Erro ao salvar aluno. Código HTTP: " + response.statusCode());
			}
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	
}
