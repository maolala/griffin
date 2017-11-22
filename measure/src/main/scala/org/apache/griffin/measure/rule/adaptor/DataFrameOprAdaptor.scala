/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.griffin.measure.rule.adaptor

import org.apache.griffin.measure.process.ProcessType
import org.apache.griffin.measure.rule.step._

case class DataFrameOprAdaptor(timeStamp: Long, adaptPhase: AdaptPhase) extends RuleAdaptor {

  def genRuleStep(param: Map[String, Any]): Seq[RuleStep] = {
    DfOprStep(getName(param), getRule(param), getDetails(param),
      getPersistType(param), getUpdateDataSource(param)) :: Nil
  }
  def adaptConcreteRuleStep(ruleStep: RuleStep, dsTmsts: Map[String, Set[Long]]): Seq[ConcreteRuleStep] = {
    ruleStep match {
      case rs @ DfOprStep(_, _, _, _, _) => rs :: Nil
      case _ => Nil
    }
  }

  def getTempSourceNames(param: Map[String, Any]): Seq[String] = {
    param.get(_name) match {
      case Some(name) => name.toString :: Nil
      case _ => Nil
    }
  }

}
