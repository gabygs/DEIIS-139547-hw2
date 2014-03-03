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
	//For future application might want to turn this N into a parameter.
	//Integer[] possibleNs={1,2,3};
	

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//get incoming text
		String inText = aJCas.getDocumentText();
		String[] lines = inText.split("\n");
		AnnotationIndex<Annotation> annot_toks = aJCas.getAnnotationIndex(Token.type);
		FSIterator<Annotation> iterate_toks = annot_toks.iterator();
		ArrayList<Annotation> token_array = new ArrayList<Annotation>();
		NGram ngrams;
		FSArray ngram_elems;
		int countTokens=0;
		
		while(iterate_toks.hasNext()) {
			countTokens++;
			token_array.add(iterate_toks.next());
		}
		
		while(iterate_toks.hasNext()) {
			
			for(int i=0; i < countTokens;i++){
				ngram_elems= new FSArray(aJCas,1);
				ngram_elems.set(0, token_array.get(i));
				
				ngrams.addToIndexes();
			}
		}
		
	}
	
	public NGram buildNGram(FSArray arr, JCas aJCas, String inText, int size_N) {
		NGram ngram = new NGram(aJCas);
		String[] lines = inText.split("\n");
		
		ngram.setElements(arr);
		ngram.setCasProcessorId(this.annotatorID);
		if(text.charAt(ant)=='Q') {
			ngram.setElementType("Question");
		} else if(text.charAt(ant)=='A') {
			ngram.setElementType("Answer");
		ngram.setBegin( ((Annotation)arr.get( 0 )).getBegin() );
		ngram.setEnd( ((Annotation)arr.get( size - 1 )).getEnd() );
		return ngram;
	}
}

