package org.resthub.web.controller;

import java.io.Serializable;
import java.util.List;

import org.resthub.web.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller interface
 *
 * @param <T>  Your resource POJO to manage, maybe an entity or DTO class
 * @param <ID> Resource id type, usually Long or String
 */
public interface RestController<T, ID extends Serializable> {

    /**
     * Create a new resource<br />
     * REST webservice published : POST /
     *
     * @param resource The resource to create
     * @return CREATED http status code if the request has been correctly processed, with updated resource enclosed in the body, usually with and additional identifier automatically created by the database
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    T create(@RequestBody T resource);

    /**
     * Update an existing resource<br/>
     * REST webservice published : PUT /{id}
     *
     * @param id       The identifier of the resource to update, usually a Long or String identifier. It is explicitely provided in order to handle cases where the identifier could be changed.
     * @param resource The resource to update
     * @return OK http status code if the request has been correctly processed, with the updated resource enclosed in the body
     * @throws NotFoundException, BadRequestException
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    T update(@PathVariable ID id, @RequestBody T resource);

    /**
     * Find all resources, and return the full collection (paginated)<br/>
     * REST webservice published : GET /?page=all
     *
     * @return OK http status code if the request has been correctly processed, with the list of all resource enclosed in the body. Be carefull, this list should be big since it will return ALL resources. In this case, consider using paginated findAll method instead.
     */
    @RequestMapping(method = RequestMethod.GET, params="page=all")
    @ResponseBody
    Page<T> findAll();
    
    /**
     * Find all resources, and return the full collection (plain list not paginated)<br/>
     * REST webservice published : GET /?page=no
     *
     * @return OK http status code if the request has been correctly processed, with the list of all resource enclosed in the body. Be carefull, this list should be big since it will return ALL resources. In this case, consider using paginated findAll method instead.
     */
    @RequestMapping(method = RequestMethod.GET, params="page=no")
    @ResponseBody
    public List<T> findAllNotPaginated();

    /**
     * Find all resources, and return a paginated collection<br/>
     * REST webservice published : GET /search?page=0&size=20
     *
     * @param page   Page number starting from 0. default to 0
     * @param size   Number of resources by pages. default to 10
     * @return OK http status code if the request has been correctly processed, with the a paginated collection of all resource enclosed in the body.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    Page<T> findPaginated(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size);

    /**
     * Find a resource by its identifier<br/>
     * REST webservice published : GET /{id}
     *
     * @param id The identifier of the resouce to find
     * @return OK http status code if the request has been correctly processed, with resource found enclosed in the body
     * @throws NotFoundException
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    T findById(@PathVariable ID id);

    /**
     * Delete all resources<br/>
     * REST webservice published : DELETE /<br/>
     * Return No Content http status code if the request has been correctly processed
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete();

    /**
     * Delete a resource by its identifier<br />
     * REST webservice published : DELETE /{id}<br />
     * Return No Content http status code if the request has been correctly processed
     *
     * @param id The identifier of the resource to delete
     * @throws NotFoundException
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable ID id);
    
}