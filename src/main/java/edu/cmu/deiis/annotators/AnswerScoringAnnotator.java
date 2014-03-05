/**
 * @author Gabriela Gongora
 *
 */

 
package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.deiis.types.*;

import java.util.*;


public class AnswerScoringAnnotator extends JCasAnnotator_ImplBase{
	String annotatorID="AnswerScoringAnnotator";
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnswerScore answer_score;
		double score=0;
		AnnotationIndex<Annotation> annot_ngrams =aJCas.getAnnotationIndex(NGram.type);
		AnnotationIndex<Annotation> annot_ans = aJCas.getAnnotationIndex(Answer.type);
		FSIterator<Annotation> ngrams_iter =annot_ngrams.iterator();
		FSIterator<Annotation> ans_iter =annot_ans.iterator();
		//NGram ngram;
		String inText = aJCas.getDocumentText();
		
		ArrayList<NGram> q_ngrams =new ArrayList<NGram>();
		ArrayList<NGram> a_ngrams =new ArrayList<NGram>();
		HashSet<String> q_ngrams_S = new HashSet<String>();
		HashSet<String> a_ngrams_S = new HashSet<String>();

        String[] lines = inText.split("\n");
        int countAns =0;
        int[] indx =new int[lines.length];
        
        System.out.println("=================== ANSWER SCORING ======================");
        //Count number of answers and max indexes length for each answer..
        for(int numL=0; numL < lines.length; numL++){
			String eachline = lines[numL];
			if(numL==0)
				indx[numL]=eachline.length();
			
			if(eachline.startsWith("A ")){
				countAns++;
				indx[numL]=eachline.length()+indx[numL-1];
			}
		}

        String[] eachAnw = new String[countAns];
      String currQuest="";
        //separate NGrams into Question and Answer types
		while(ngrams_iter.hasNext()){
			NGram ngram =(NGram)ngrams_iter.next();
			if(ngram.getElementType().equals("Question")){
				q_ngrams.add(ngram);
				String dummy =inText.substring(ngram.getBegin(),ngram.getEnd()); 
				q_ngrams_S.add(dummy);
				currQuest=dummy;
				System.out.println("CurrQuest:"+currQuest);
				
			}
			if(ngram.getElementType().equals("Answer")){
				a_ngrams.add(ngram);
				String dummy =inText.substring(ngram.getBegin(),ngram.getEnd()); 
				a_ngrams_S.add(dummy);
				
				for(int anws=0;anws<countAns;anws++){
					if(ngram.getEnd()>indx[anws] && ngram.getEnd()<indx[anws])
						eachAnw[anws]=eachAnw[anws]+" "+dummy;	
					System.out.println("EachAns:"+eachAnw[anws]);
					
				}
				
			}	
		}
		
		//Feed Answers, one by one...
		
		Answer answer_a=null;
		System.out.println("CountAns: "+countAns);
		//Counting is working excelent :)
		
		while(ans_iter.hasNext()){
		for(int anws=0;anws<countAns;anws++){
			answer_a = (Answer)ans_iter.next();
			//answer_a = ans_iter.next();
			
			score=calculateScore(eachAnw[anws],currQuest);
			System.out.println("Answer SCORE: "+score);
			answer_score = new AnswerScore(aJCas);
			answer_score.setBegin(answer_a.getBegin());
			answer_score.setEnd(answer_a.getEnd());
			answer_score.setAnswer(answer_a);
			answer_score.setScore(score);
			answer_score.addToIndexes();
			answer_score.setCasProcessorId(annotatorID);	
			}
		}

        System.out.println("==========================================================");
        System.out.println("");
	}

	//Calculate score according to Jaccard Index
	public double calculateScore(String answer_ngrams,String question_ngrams){
		HashSet<String> intersected_ngrams = new HashSet<String>();
		HashSet<String> unique_ngrams = new HashSet<String>();
          
		intersected_ngrams.addAll(Arrays.asList(answer_ngrams.split("\\s+")));
		//boolean addAll= unique_ngrams.addAll(Arrays.asList(question_ngrams.split("\\s+")));
		int sizeIntersected = intersected_ngrams.size(); 
		int union = sizeIntersected + unique_ngrams.size();
		int intersection = intersected_ngrams.size();
		
		return (double)intersection/union;
	}
}
