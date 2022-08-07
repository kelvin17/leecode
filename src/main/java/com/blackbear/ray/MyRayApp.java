package com.blackbear.ray;

import io.ray.api.ActorHandle;
import io.ray.api.ObjectRef;
import io.ray.api.Ray;
import org.testng.Assert;

public class MyRayApp {

    public static void main(String[] args) {
        System.setProperty("ray.run-mode", "SINGLE_PROCESS");
        Ray.init();

        ActorHandle<Counter> counter = Ray.actor(Counter::new).remote();
        for (int i = 0; i < 5; i++) {
            counter.task(Counter::increment).remote();
        }

        ObjectRef<Integer> objectRef = counter.task(Counter::getValue).remote();
        System.out.printf("result = %d\n", objectRef.get());
        Assert.assertEquals((int) objectRef.get(), 5);
    }
}
