package br.com.azulseguros.restpiloto.resources;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jackson.Formatted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import azulseguros.healthcheck.IHealthCheckService;
import br.com.azulseguros.restpiloto.entity.Participante;
import br.com.azulseguros.restpiloto.entity.Projeto;
import br.com.azulseguros.restpiloto.repository.ParticipanteRepositoryImpl;
import br.com.azulseguros.restpiloto.repository.ProjetoRepositoryImpl;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParticipanteResource implements IHealthCheckService {
	private static final Logger logger = LoggerFactory.getLogger(ParticipanteResource.class);
	private static final String CONTENT_TYPE = "Content-Type";
	
	@Inject
	ParticipanteRepositoryImpl repository;

	@Inject
	ProjetoRepositoryImpl repositoryProjeto;
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getParticipantes() {
		logger.info("Início");
		Response response = null;
		List<Participante> entity = repository.query(null);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@GET
	@Path("/Participante/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getParticipante(@PathParam("id") Integer id) {
		logger.info("Início");
		Response response = null;
		if (id == null || id.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		}
		List<Participante> entity = repository.query(id);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@PUT
	@Path("/Participante/{codParticipante}")
	@Formatted
	//short codParticipante, String dscParticipante, String statusParticipante
	public Response addParticipante(@PathParam("codParticipante") Integer codParticipante, @QueryParam("idProjeto") Integer idProjeto) {
		logger.info("Início");
		if (codParticipante == null || codParticipante.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("código do Participante não pode ser nulo!").build();
		} else if (idProjeto == null || idProjeto.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("descrição do Participante não pode ser nulo!").build();
		}
		List<Projeto> projetos = repositoryProjeto.query(idProjeto);
		if(projetos.size()>1) return Response.status(Response.Status.CONFLICT).entity("Há mais de um projeto com o mesmo id!").build();
		Participante novo = new Participante(codParticipante, projetos.get(0));
		repository.add(novo);
		logger.info("Fim");
		return Response.status(Response.Status.CREATED).entity("Participante inserido com sucesso!").build();
	}

	@POST
	@Path("/Participante/{codParticipante}")
	@Formatted
	//short codParticipante, String dscParticipante, String statusParticipante
	public Response updateParticipante(@PathParam("codParticipante") Integer codParticipante, @QueryParam("idProjeto") Integer idProjeto) {
		logger.info("Início");
		if (codParticipante == null || codParticipante.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("código do Participante não pode ser nulo!").build();
		} else if (idProjeto == null || idProjeto.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("descrição do Participante não pode ser nulo!").build();
		}
		List<Projeto> projetos = repositoryProjeto.query(idProjeto);
		if(projetos.size()>1) return Response.status(Response.Status.CONFLICT).entity("Há mais de um projketo com o mesmo id!").build();
		Participante novo = new Participante(codParticipante, projetos.get(0));
		repository.update(novo);
		logger.info("Fim");
		return Response.status(Response.Status.CREATED).entity("Participante atualizado com sucesso!").build();
	}
	
	@DELETE
	@Path("/Participante/{idParticipante}")
	@Formatted
	public Response removeParticipante(@PathParam("idParticipante") Integer idParticipante) {
		logger.info("Início");
		if (idParticipante == null || idParticipante.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id do Participante não pode ser nulo!").build();
		}
		repository.remove(idParticipante);
		logger.info("Fim");
		return Response.ok("Participante removido com sucesso!", MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/health_check")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response health_check(@QueryParam("timeout") int timeout) {
		//TODO -IMPLEMENTAR
		logger.info("Início");
		logger.info("Fim");
		return null;
	}

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response options() {
		logger.info("Início");
		Response response = Response.status(200).header("Allow", "POST, PUT, GET, DELETE, OPTIONS, HEAD")
				.header("Content-Type", MediaType.APPLICATION_JSON).header("Content-Length", "0").build();
		logger.info("Fim");
		return response;
	}

	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response head() {
		logger.info("Início");
		Response retorno = Response.ok().header(ParticipanteResource.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		logger.info("Fim");
		return retorno;
	}
	
	
}
