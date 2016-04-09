package domainapp.app.services.homepage;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.background.BackgroundService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.core.runtime.sessiontemplate.AbstractIsisSessionTemplate;

import domainapp.dom.simple.SimpleObject;

public class BackgroundJob extends AbstractIsisSessionTemplate {

    @Override
    protected void doExecuteWithTransaction(Object context) {
	List<SimpleObject> allInstances = repositoryService.allInstances(SimpleObject.class);
	if (allInstances.isEmpty()) {
	    return;
	}
	
	backgroundService.execute(allInstances.get(0)).updateName((String) context);
    }

    @Inject
    RepositoryService repositoryService;
    
    @Inject
    BackgroundService backgroundService;
}
