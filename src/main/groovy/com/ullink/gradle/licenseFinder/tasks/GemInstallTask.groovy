package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import org.gradle.api.Project

import javax.inject.Inject

class GemInstallTask extends JRubyExec {
    private final String licenseFinder = "license_finder"
    private final String licenseFinderVersion = "5.10.2"
    private ArrayList<String> params = []
    @Inject
    GemInstallTask(Project project) {
        super()
        this.setProxy("http")
        this.script "gem"
        this.scriptArgs "install", params.join(' ') ,licenseFinder, "-v ${licenseFinderVersion}"
    }

    void setProxy(String protocol) {
        String hostProperty = "systemProp.${protocol}.proxyHost"
        String portProperty = "systemProp.${protocol}.proxyPort"
        if (project.property(hostProperty) && project.property(portProperty))
            params.add("--http-proxy=${protocol}://${project.property(hostProperty)}:${project.property(portProperty)}")
    }
}
