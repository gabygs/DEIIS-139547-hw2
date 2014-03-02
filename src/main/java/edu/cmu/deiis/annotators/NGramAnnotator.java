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
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.deiis.types.*;

import java.util.*;

public class NGramAnnotator extends JCasAnnotator_ImplBase{
	String annotatorID="NGramAnnotator";

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
	}

}
