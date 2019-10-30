/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/09/2019
 */
package com.linecode.bdd.protocolo;

import org.junit.runner.RunWith;

import com.linecode.bdd.config.DateCon;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverters;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/protocolo" })
@XStreamConverters(@XStreamConverter(DateCon.class))
public class ProtocoloTesteConfiguracao  {

}
