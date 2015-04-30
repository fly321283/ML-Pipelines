/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.streaming.incrementalML.test.inspector

import org.apache.flink.streaming.incrementalML.inspector.PageHinkleyTest
import org.scalatest.{Matchers, FlatSpec}

class PageHinkleyTestSuite
  extends FlatSpec
  with Matchers {

  behavior of "The Page Hinkley Test change detector implementation"

  it should "detect drift on the input data stream" in {

    import PageHinkleyTestData._

    val pht = PageHinkleyTest(lambda,delta,minNumberOfInstances)
    var changesDetected: Int = 0

    for (dataPoint <- data){
      val res = pht.input(dataPoint)
      if (res){
        println(pht.toString+"\n")
        changesDetected += 1
        pht.reset()
      }
    }
    println(pht.toString)
    println (s"\nTotal number of detected changed in the input dataStream: $changesDetected")

    changesDetected should be >= 3

  }
}
