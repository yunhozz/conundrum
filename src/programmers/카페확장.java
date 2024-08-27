package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 카페확장 {

    public int solution(int[] menu, int[] order, int k) {
        Queue<Customer> customers = new LinkedList<>();
        int currentTime = 0;
        int size = 0;

        for (int i = 0; i < order.length; i++) {
            int arrivalTime = i * k;
            int preparationTime = menu[order[i]];
            while (!customers.isEmpty() && customers.peek().endTime <= arrivalTime) {
                customers.poll();
            }

            currentTime = customers.isEmpty() ? arrivalTime + preparationTime : currentTime + preparationTime;
            Customer customer = new Customer(arrivalTime, currentTime);
            customers.add(customer);
            size = Math.max(customers.size(), size);
        }

        return size;
    }

    private static class Customer {
        int startTime;
        int endTime;

        public Customer(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}