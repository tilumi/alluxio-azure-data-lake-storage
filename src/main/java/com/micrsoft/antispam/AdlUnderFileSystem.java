/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package com.micrsoft.antispam;

import alluxio.AlluxioURI;
import alluxio.underfs.UnderFileSystemConfiguration;
import alluxio.underfs.hdfs.HdfsUnderFileSystem;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class AdlUnderFileSystem extends HdfsUnderFileSystem {
  private static final Logger LOG = LoggerFactory.getLogger(AdlUnderFileSystem.class);

  /** Constant for the wasb URI scheme. */
  public static final String SCHEME = "adls://";

  /**
   * Prepares the configuration for this Wasb as an HDFS configuration.
   *
   * @param conf the configuration for this UFS
   * @return the created configuration
   */
  public static Configuration createConfiguration(UnderFileSystemConfiguration conf) {
    return HdfsUnderFileSystem.createConfiguration(conf);
  }

  public static AdlUnderFileSystem createInstance(AlluxioURI uri,
                                                  UnderFileSystemConfiguration conf) {
    Configuration wasbConf = createConfiguration(conf);
    return new AdlUnderFileSystem(uri, conf, wasbConf);
  }

  public AdlUnderFileSystem(AlluxioURI ufsUri, UnderFileSystemConfiguration conf,
                            final Configuration wasbConf) {
    super(ufsUri, conf, wasbConf);
  }

  @Override
  public String getUnderFSType() {
    return "adl";
  }
}
