/**
 * @author Gabriela Gongora
 *
 */

package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.deiis.types.*;

import java.util.*;

public class NGramAnnotator extends JCasAnnotator_ImplBase{
	String annotatorID="NGramAnnotator";
	//For future application might want to turn this N into a parameter.
	//Integer[] possibleNs={1,2,3};
	
	
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//Annotation's index from JCas pipeline 
		AnnotationIndex<Annotation> an_toks = aJCas.getAnnotationIndex(Token.type);
		//Iterators for annotations
		FSIterator<Annotation> iter_toks = an_toks.iterator();
		//get incoming text
		//String inText = aJCas.getDocumentText();
		//split lines
		//String[] lines = inText.split("\n");
		ArrayList<Annotation> tokens = new ArrayList<Annotation>();
		FSArray tok_elems;
		NGram ngram;
		Token tok;
				
		while(iter_toks.hasNext()){
			tokens.add(iter_toks.next());
		}
		String type_;
		for(int i=0; i<tokens.size(); i++) {
			tok_elems = new FSArray(aJCas, 1);
			tok_elems.set(0, tokens.get(i));
			tok=(Token)tokens.get(i);
			type_=tok.getToken_type();
			ngram = buildNGram(tok_elems,type_, aJCas, 1);
			ngram.addToIndexes();
	
			if((i+1)<tokens.size()) {
				tok_elems= new FSArray(aJCas, 2);
				tok_elems.set(0, tokens.get(i));
				tok_elems.set(1, tokens.get(i+1));
				tok=(Token)tokens.get(i);
				type_=tok.getToken_type();
				ngram = buildNGram(tok_elems,type_, aJCas, 2);
				ngram.addToIndexes();
			}
			if((i+1)<tokens.size() && (i+2)<tokens.size()) {
				tok_elems = new FSArray(aJCas, 3);
				tok_elems.set(0, tokens.get(i));
				tok_elems.set(1, tokens.get(i+1));
				tok_elems.set(2, tokens.get(i+2));
				tok=(Token)tokens.get(i);
				type_=tok.getToken_type();
				ngram = buildNGram(tok_elems,type_, aJCas, 3);
				ngram.addToIndexes();
			}
		}
		
		
	}

	public NGram buildNGram(FSArray elems, String type_grams, JCas aJCas, int size_N) {
		NGram ngram = new NGram(aJCas);
		ngram.setElements(elems);
		ngram.setCasProcessorId(this.annotatorID);
		ngram.setElementType(type_grams);
		ngram.setBegin( ((Annotation)elems.get( 0 )).getBegin() );
		ngram.setEnd( ((Annotation)elems.get( size_N - 1 )).getEnd() );
		
		return ngram;
	}
}

