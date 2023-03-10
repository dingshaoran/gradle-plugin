import com.android.resource.CheckResourceTask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Plugin entrance*/
public class CheckResourcePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("checkResource", CheckResourceExtension.class);
        project.afterEvaluate(proj -> {
            CheckResourceExtension extension = project.getExtensions().getByType(CheckResourceExtension.class);
            CheckResourceTask.register(project, extension);
        });
    }
}