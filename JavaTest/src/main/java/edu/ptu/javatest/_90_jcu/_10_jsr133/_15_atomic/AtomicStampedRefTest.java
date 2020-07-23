package edu.ptu.javatest._90_jcu._10_jsr133._15_atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedRefTest {
    @Test
    public void testAtomicStampedRef(){
        AtomicStampedReference<String> reference = new AtomicStampedReference<String>("1",1);
        reference.compareAndSet("1","2",reference.getStamp(),reference.getStamp()+1);
        System.out.println("reference.getReference() = " + reference.getReference());
    }
}
