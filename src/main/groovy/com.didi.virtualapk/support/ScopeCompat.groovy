package com.didi.virtualapk.support

import com.android.build.gradle.internal.scope.VariantScope
import com.didi.virtualapk.os.Build
import com.didi.virtualapk.utils.Reflect
import org.gradle.api.Project

final class ScopeCompat {


    def static getArtifact(Project project, String name) {
        if (Build.isSupportVersion(project, Build.VERSION_CODE.V3_2_X)) {
            return Reflect.on("com.android.build.gradle.internal.scope.InternalArtifactType")
                    .field(name).get()
        } else {
            return Reflect.on("com.android.build.gradle.internal.scope.TaskOutputHolder\$TaskOutputType")
                    .field(name).get()
        }
    }


    def static getArtifactFile(VariantScope scope, Project project, def artifact) {
        if (Build.isSupportVersion(project, Build.VERSION_CODE.V3_2_X)) {
            return scope.artifacts.getFinalArtifactFiles(artifact)
        } else {
            return scope.getOutput(artifact)
        }
    }

}
