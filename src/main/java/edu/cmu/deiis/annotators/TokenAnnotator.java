/**
 * @author Gabriela Gongora
 *
 */
package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.deiis.types.*;


public class TokenAnnotator extends JCasAnnotator_ImplBase  {
	String annotatorID="TokenAnnotator";

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		
	}
	
}
