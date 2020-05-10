package com.rbs.multimodule.backend.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Manifest {

	private final static Logger logger = LoggerFactory.getLogger(Manifest.class);

	public static String getVersion() {
		String buildTime = null;
		Enumeration<?> resEnum;
		try {
			resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
			while ((buildTime == null) && (resEnum.hasMoreElements())) {
				URL url = (URL) resEnum.nextElement();
				try (InputStream is = url.openStream()) {
					if (is != null) {
						buildTime = readManifest(is);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Could not read manifest for build time", e);
		}
		if (buildTime == null) {
			buildTime = getDefaultBuildTime();
		}
		return buildTime;
	}

	public static String getVersion(ServletContext servletContext) {
		String buildTime = null;
		try (InputStream is = servletContext.getResourceAsStream("/" + JarFile.MANIFEST_NAME)) {
			if (is != null) {
				buildTime = readManifest(is);
			}
		} catch (Exception e) {
			logger.error("Could not read servlet manifest for build time", e);
		}
		if (buildTime == null) {
			buildTime = getDefaultBuildTime();
		}
		return buildTime;

	}

	private static String readManifest(InputStream is) throws IOException {
		java.util.jar.Manifest manifest = new java.util.jar.Manifest(is);
		Attributes mainAttribs = manifest.getMainAttributes();
		return mainAttribs.getValue("boot-build-time");
	}

	private static String getDefaultBuildTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
		return (sdf.format(new Date()));
	}
}