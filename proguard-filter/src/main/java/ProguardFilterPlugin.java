import com.android.proguard.ProguardFilterTask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Plugin entrance*/
public class ProguardFilterPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("proguardFilter", ProguardFilterExtension.class);
        project.afterEvaluate(proj -> {
            ProguardFilterExtension extension = project.getExtensions().getByType(ProguardFilterExtension.class);
            ProguardFilterTask.register(project, extension.getConfig());
        });
    }
}