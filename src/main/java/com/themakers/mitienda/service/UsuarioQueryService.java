package com.themakers.mitienda.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.themakers.mitienda.domain.Usuario;
import com.themakers.mitienda.domain.*; // for static metamodels
import com.themakers.mitienda.repository.UsuarioRepository;
import com.themakers.mitienda.service.dto.UsuarioCriteria;
import com.themakers.mitienda.service.dto.UsuarioDTO;
import com.themakers.mitienda.service.mapper.UsuarioMapper;

/**
 * Service for executing complex queries for {@link Usuario} entities in the database.
 * The main input is a {@link UsuarioCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UsuarioDTO} or a {@link Page} of {@link UsuarioDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UsuarioQueryService extends QueryService<Usuario> {

    private final Logger log = LoggerFactory.getLogger(UsuarioQueryService.class);

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioQueryService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * Return a {@link List} of {@link UsuarioDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findByCriteria(UsuarioCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Usuario> specification = createSpecification(criteria);
        return usuarioMapper.toDto(usuarioRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UsuarioDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findByCriteria(UsuarioCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Usuario> specification = createSpecification(criteria);
        return usuarioRepository.findAll(specification, page)
            .map(usuarioMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UsuarioCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Usuario> specification = createSpecification(criteria);
        return usuarioRepository.count(specification);
    }

    /**
     * Function to convert {@link UsuarioCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Usuario> createSpecification(UsuarioCriteria criteria) {
        Specification<Usuario> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Usuario_.id));
            }
            if (criteria.getSegundoNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegundoNombre(), Usuario_.segundoNombre));
            }
            if (criteria.getSegundoApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSegundoApellido(), Usuario_.segundoApellido));
            }
            if (criteria.getNumDocumento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumDocumento(), Usuario_.numDocumento));
            }
            if (criteria.getCelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCelular(), Usuario_.celular));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Usuario_.direccion));
            }
            if (criteria.getGenero() != null) {
                specification = specification.and(buildSpecification(criteria.getGenero(), Usuario_.genero));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Usuario_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getCuidadId() != null) {
                specification = specification.and(buildSpecification(criteria.getCuidadId(),
                    root -> root.join(Usuario_.cuidad, JoinType.LEFT).get(ElementoLista_.id)));
            }
        }
        return specification;
    }
}
