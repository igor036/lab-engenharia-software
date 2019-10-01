/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/09/2019
 */
package com.linecode.docente;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/docente" })
public class TesteConfiguracao  {

}
