package br.com.caelum.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

// fases do ciclo de vida

/*FASE: RESTORE_VIEW 1
FASE: APPLY_REQUEST_VALUES 2
FASE: PROCESS_VALIDATIONS 3
FASE: UPDATE_MODEL_VALUES 4
FASE: INVOKE_APPLICATION 5
Livro escrito por :Machado de Assis
FASE: RENDER_RESPONSE 6 */

public class LogPhaseListener implements PhaseListener{

	@Override
	// ocorre antes de uma fase
	public void afterPhase(PhaseEvent event) {
		
		
	}

	@Override
	//ocorre depois de uma fase
	public void beforePhase(PhaseEvent event) {
		System.out.println("FASE: " + event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}

	 
	
	
}
