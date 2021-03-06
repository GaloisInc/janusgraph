// Copyright 2017 JanusGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.janusgraph.hadoop.scan;

import org.janusgraph.diskstorage.configuration.Configuration;
import org.janusgraph.diskstorage.configuration.ReadConfiguration;
import org.janusgraph.diskstorage.keycolumnvalue.scan.ScanJob;
import org.janusgraph.graphdb.olap.VertexScanJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractHadoopScanRunner<R> {

    private static final Logger log =
            LoggerFactory.getLogger(CassandraHadoopScanRunner.class);

    protected final ScanJob scanJob;
    protected final VertexScanJob vertexScanJob;
    protected String scanJobConfRoot;
    protected Configuration scanJobConf;
    protected ReadConfiguration janusgraphConf;
    protected org.apache.hadoop.conf.Configuration baseHadoopConf;

    public AbstractHadoopScanRunner(ScanJob scanJob) {
        this.scanJob = scanJob;
        this.vertexScanJob = null;
    }

    public AbstractHadoopScanRunner(VertexScanJob vertexScanJob) {
        this.vertexScanJob = vertexScanJob;
        this.scanJob = null;
    }

    protected abstract R self();

    public R scanJobConfRoot(String jobConfRoot) {
        this.scanJobConfRoot = jobConfRoot;
        return self();
    }

    public R scanJobConf(Configuration jobConf) {
        this.scanJobConf = jobConf;
        return self();
    }

    public R baseHadoopConf(org.apache.hadoop.conf.Configuration baseHadoopConf) {
        this.baseHadoopConf = baseHadoopConf;
        return self();
    }

    public R useJanusGraphConfiguration(ReadConfiguration janusgraphConf) {
        this.janusgraphConf = janusgraphConf;
        return self();
    }
}
