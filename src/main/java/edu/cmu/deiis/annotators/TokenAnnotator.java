/**
 * @author Gabriela Gongora
 *
 */
package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.*;


public class TokenAnnotator extends JCasAnnotator_ImplBase  {
	String annotatorID="TokenAnnotator";

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//get incoming text
		String inText = aJCas.getDocumentText();
		//split lines
		String[] lines = inText.split("\n");
		Token token;
        int indexCount=0;
        int dummy=0;
        int q_a=0;
        
        for(int L=0;L<lines.length;L++){
        	System.out.println(lines[L].toString());
        	System.out.println("Length: "+lines[L].length());
        }
        
		for(int numL=0; numL < lines.length; numL++){
			String eachline = lines[numL];
			String cutline=null;
	        String[] words;
			
			if(eachline.startsWith("Q ")){
				cutline=eachline.substring(2, eachline.length());
				indexCount=indexCount+2;
				q_a=1;
			}else if(eachline.startsWith("A ")){
				cutline=eachline.substring(4, eachline.length());
				indexCount=indexCount+4;
				q_a=0;
			}
			
			//words = cutline.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			//words = cutline.replaceAll("[']","_").toLowerCase().split("\\s+");
			words = cutline.replaceAll("['.,?!]"," ").toLowerCase().split("\\s+");
			
			for(int ws=0;ws<words.length;ws++){
					token=new Token(aJCas);
					int beg_ind=0,end_ind=0;
					
		            System.out.println(words[ws].toString());
		            beg_ind = cutline.toLowerCase().indexOf(words[ws])+indexCount+dummy;
		            end_ind=beg_ind+words[ws].length();
		            System.out.println("i: "+beg_ind);
		            System.out.println("j: "+end_ind);
		            
		            if(q_a==1){
		            	token.setToken_type("Q");
		            }else if(q_a==0){
		            	token.setToken_type("A");
		            }
		            token.setBegin(beg_ind);
		            token.setEnd(end_ind);
		            token.setCasProcessorId(this.annotatorID);
		            token.addToIndexes();
		        }
				indexCount=0;
				dummy=dummy+lines[numL].length()+1;
			}
	}
}
	

