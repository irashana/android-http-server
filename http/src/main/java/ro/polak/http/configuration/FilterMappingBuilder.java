/**************************************************
 * Android Web Server
 * Based on JavaLittleWebServer (2008)
 * <p/>
 * Copyright (c) Piotr Polak 2018-2018
 **************************************************/

package ro.polak.http.configuration;

import java.util.regex.Pattern;

import ro.polak.http.configuration.impl.FilterMappingImpl;
import ro.polak.http.servlet.Filter;

/**
 * Utility for building filter mapping configuration.
 *
 * @author Piotr Polak piotr [at] polak [dot] ro
 * @since 201803
 */
public class FilterMappingBuilder {

    private final ServletContextBuilder servletContextBuilder;
    private Pattern urlPattern;
    private Pattern urlExcludedPattern;
    private Class<? extends Filter> clazz;

    /**
     * Created a mapping builder. This constructor should be package scoped.
     *
     * @param servletContextBuilder
     */
    FilterMappingBuilder(ServletContextBuilder servletContextBuilder) {
        this.servletContextBuilder = servletContextBuilder;
    }

    public FilterMappingBuilder withUrlPattern(Pattern urlPattern) {
        this.urlPattern = urlPattern;
        return this;
    }

    public FilterMappingBuilder withUrlExcludedPattern(Pattern urlExcludedPattern) {
        this.urlExcludedPattern = urlExcludedPattern;
        return this;
    }

    public FilterMappingBuilder withFilterClass(Class<? extends Filter> clazz) {
        this.clazz = clazz;
        return this;
    }

    public ServletContextBuilder end() {
        servletContextBuilder.withFilterMapping(new FilterMappingImpl(urlPattern, urlExcludedPattern, clazz));
        return servletContextBuilder;
    }
}