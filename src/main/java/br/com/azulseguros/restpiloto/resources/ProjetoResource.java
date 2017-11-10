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
import br.com.azulseguros.restpiloto.entity.Projeto;
import br.com.azulseguros.restpiloto.repository.ProjetoRepositoryImpl;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjetoResource implements IHealthCheckService {
	private static final Logger logger = LoggerFactory.getLogger(ProjetoResource.class);
	private static final String CONTENT_TYPE = "Content-Type";
	
	@Inject
	ProjetoRepositoryImpl repository;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getProjetos() {
		logger.info("Início");
		Response response = null;
		List<Projeto> entity = repository.query(null);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@GET
	@Path("/Projeto/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	public Response getProjeto(@PathParam("nome") String nome) {
		logger.info("Início");
		Response response = null;
		if (id == null || id.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id não pode ser nulo!").build();
		}
		List<Projeto> entity = repository.query(id);
		if (entity.size() != 0) {
			response = Response.ok().entity(entity).build();
		} else {
			response = Response.noContent().build();
		}
		logger.info("Fim");
		return response;
	}

	@PUT
	@Path("/Projeto/{codProjeto}")
	@Formatted
	//short codProjeto, String dscProjeto, String statusProjeto
	public Response addProjeto(@PathParam("codProjeto") Short codProjeto, @QueryParam("dscProjeto") String dscProjeto,
			@QueryParam("statusProjeto") String statusProjeto) {
		logger.info("Início");
		if (codProjeto == null || codProjeto.shortValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("código do projeto não pode ser nulo!").build();
		} else if (dscProjeto == null || dscProjeto.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("descrição do projeto não pode ser nulo!").build();
		} else if (statusProjeto == null || statusProjeto.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("status do projeto não pode ser nulo!").build();
		}

		Projeto novo = new Projeto(codProjeto, dscProjeto, statusProjeto);
		repository.add(novo);
		logger.info("Fim");
		return Response.status(Response.Status.CREATED).entity("Projeto inserido com sucesso!").build();
	}

	@POST
	@Path("/Projeto/{codProjeto}")
	@Formatted
	//short codProjeto, String dscProjeto, String statusProjeto
	public Response updateProjeto(@PathParam("codProjeto") Short codProjeto, @QueryParam("dscProjeto") String dscProjeto,
			@QueryParam("statusProjeto") String statusProjeto) {
		logger.info("Início");
		if (codProjeto == null || codProjeto.shortValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("código do projeto não pode ser nulo!").build();
		} else if (dscProjeto == null || dscProjeto.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("descrição do projeto não pode ser nulo!").build();
		} else if (statusProjeto == null || statusProjeto.trim().length() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("status do projeto não pode ser nulo!").build();
		}

		Projeto novo = new Projeto(codProjeto, dscProjeto, statusProjeto);
		repository.update(novo);
		logger.info("Fim");
		return Response.status(Response.Status.CREATED).entity("Projeto atualizado com sucesso!").build();
	}
	
	@DELETE
	@Path("/Projeto/{idProjeto}")
	@Formatted
	public Response removeProjeto(@PathParam("idProjeto") Integer idProjeto) {
		logger.info("Início");
		if (idProjeto == null || idProjeto.intValue() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("id do projeto não pode ser nulo!").build();
		}
		repository.remove(idProjeto);
		logger.info("Fim");
		return Response.ok("Projeto removido com sucesso!", MediaType.APPLICATION_JSON).build();

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
		Response retorno = Response.ok().header(ProjetoResource.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		logger.info("Fim");
		return retorno;
	}
	
	
}
