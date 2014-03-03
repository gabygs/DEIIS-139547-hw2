

/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sun Mar 02 16:54:24 CST 2014
 * XML source: /Users/IBAGNOG/Documents/workspace/hw2-139547/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: elements_tokens

  /** getter for elements_tokens - gets Save tokens in this array.
   * @generated
   * @return value of the feature 
   */
  public StringArray getElements_tokens() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_elements_tokens == null)
      jcasType.jcas.throwFeatMissing("elements_tokens", "edu.cmu.deiis.types.Token");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens)));}
    
  /** setter for elements_tokens - sets Save tokens in this array. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setElements_tokens(StringArray v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_elements_tokens == null)
      jcasType.jcas.throwFeatMissing("elements_tokens", "edu.cmu.deiis.types.Token");
    jcasType.ll_cas.ll_setRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for elements_tokens - gets an indexed value - Save tokens in this array.
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getElements_tokens(int i) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_elements_tokens == null)
      jcasType.jcas.throwFeatMissing("elements_tokens", "edu.cmu.deiis.types.Token");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens), i);}

  /** indexed setter for elements_tokens - sets an indexed value - Save tokens in this array.
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setElements_tokens(int i, String v) { 
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_elements_tokens == null)
      jcasType.jcas.throwFeatMissing("elements_tokens", "edu.cmu.deiis.types.Token");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Token_Type)jcasType).casFeatCode_elements_tokens), i, v);}
  }

    