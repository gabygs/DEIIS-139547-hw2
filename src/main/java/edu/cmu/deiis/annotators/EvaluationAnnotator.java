/**
 * @author Gabriela Gongora
 *
 */

 
package edu.cmu.deiis.annotators;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import edu.cmu.deiis.types.*;

import java.util.*;



public class EvaluationAnnotator extends JCasAnnotator_ImplBase{
	
	//Receiving a specific N to calculate precision@N
	Integer Nval=null; 
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException{
		super.initialize(aContext);
		
		//get Config. parameter value N.
		this.Nval = (Integer)aContext.getConfigParameterValue("N");
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
	}


}
