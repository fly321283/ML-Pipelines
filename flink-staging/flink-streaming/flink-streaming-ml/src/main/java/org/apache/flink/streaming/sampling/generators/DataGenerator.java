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

package org.apache.flink.streaming.sampling.generators;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.sampling.helpers.StreamTimestamp;

/**
 * Created by marthavk on 2015-04-24.
 */
public class DataGenerator extends RichMapFunction<GaussianDistribution, Tuple3<Double, StreamTimestamp, Long>> {

	long index = 0;

	@Override
	public Tuple3<Double, StreamTimestamp, Long> map(GaussianDistribution value) throws Exception {

		//value
		Double rand = value.generate();

		//timestamp
		final StreamTimestamp t = new StreamTimestamp();

		//order
		index++;

		return new Tuple3<Double, StreamTimestamp, Long>(rand, t, index);
	}


}