package com.yu.plugin;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

/**
 * @author yuhangbin
 * @date 2022/1/23
 **/
@Mojo(name = "dependency-counter", defaultPhase = LifecyclePhase.COMPILE)
public class DependencyCounterMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	MavenProject mavenProject;

	@Parameter(property = "scope")
	String scope;

	@Override public void execute() throws MojoExecutionException, MojoFailureException {
		List<Dependency> dependencies = mavenProject.getDependencies();
		long dependencyCount = dependencies.stream().count();
		getLog().info("Number of dependencies: " + dependencyCount);
	}
}
