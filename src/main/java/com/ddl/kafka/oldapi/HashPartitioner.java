package com.ddl.kafka.oldapi;

import com.google.common.math.IntMath;
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class HashPartitioner implements Partitioner {

  public HashPartitioner(VerifiableProperties verifiableProperties) {}

  @Override
  public int partition(Object key, int numPartitions) {
    if ((key instanceof Integer)) {
      return IntMath.mod(Math.abs(Integer.parseInt(key.toString())), numPartitions);
    }
    return Math.abs(key.hashCode() % numPartitions);
  }

  public static void main(String[] args) {
    System.out.println(IntMath.mod(3, 4));
  }
}


