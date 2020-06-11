package com.winston.practice.google;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RangeSetTest2 {

    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 01, 01))
          .endDate(LocalDate.of(2020, 12, 01))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .name("rate---1")
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 01, 01))
          .endDate(LocalDate.of(2020, 05, 01))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---2")
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 06, 01))
          .endDate(LocalDate.of(2020, 11, 01))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .name("rate---3")
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 07, 01))
          .endDate(LocalDate.of(2020, 07, 9))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .name("rate---4")
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 07, 07))
          .endDate(LocalDate.of(2020, 07, 8))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .name("rate---5")
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 05, 01))
          .endDate(LocalDate.of(2020, 05, 05))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---6")
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 05, 01))
          .endDate(LocalDate.of(2020, 05, 07))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---7")
          .days(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7))
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 05, 01))
          .endDate(LocalDate.of(2020, 05, 02))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---8")
          .days(Lists.newArrayList(4, 5, 6))
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 05, 01))
          .endDate(LocalDate.of(2020, 05, 01))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---9")
          .days(Lists.newArrayList(1))
          .build());

        tree.addFromRoot(Rate.builder().startDate(LocalDate.of(2020, 05, 02))
          .endDate(LocalDate.of(2020, 05, 02))
          .startTime(LocalTime.of(1, 0))
          .endTime(LocalTime.of(1, 0))
          .name("rate---10")
          .days(Lists.newArrayList(3))
          .build());

        tree.toListFromRoot().forEach(System.err::println);
        /**
         * 1、理想方案 是当startDate 和 endDate一样的时候  需要前端计算出其对应的days传给后端。或者后端也可以自己提前计算
         */
//        ArrayList<Integer> integers = Lists.newArrayList(3);
//        ArrayList<Integer> integers2 = Lists.newArrayList(1, 2, 3);
//        System.err.println(CollectionUtils.containsAny(integers, integers2));
//        System.err.println(integers);
//        System.err.println(integers2);
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Rate {
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private long priority;
        private List<Integer> days;
    }

    @Data
    static class Node {
        //多叉树  包含多个节点
        public List<Node> nodes = new ArrayList<>();
        //节点数据
        private Rate data;

        public Node(Rate data) {
            this.data = data;
        }
    }

    @Data
    static class Tree {
        //根节点  设置为 null
        private Node root = new Node(null);

        //获取根节点
        public Node getRoot() {
            return this.root;
        }

        //添加方法重载
        public void addFromRoot(Rate data) {
            this.addFromNode(data, this.getRoot());
        }

        public void addFromNode(Rate data, Node currentNode) {
            //为空 表示当前节点  没有子节点  则不再遍历了，新数据  就是当前节点的 第一个子节点
            List<Node> list = currentNode.nodes;
            if (list.isEmpty()) {
                list.add(new Node(data));
                return;
            }
            boolean nonConnect = true;
            Range<LocalDate> newDateRange = Range.closedOpen(data.getStartDate(), data.getEndDate());

            for (int i = 0; i < list.size(); i++) {
                Node item = list.get(i);
                Range<LocalDate> tempDateRange = Range.closedOpen(item.getData().getStartDate(), item.getData().getEndDate());
                //新的 包含了 当前节点  那么它应该替换当前节点的位置  并且把当前节点  作为他的第一个子节点
                if (newDateRange.equals(tempDateRange)) {
                    nonConnect = false;
                    //日期想等的话   再比较时间
                    Range<LocalTime> newTimeRange = Range.closedOpen(data.getStartTime(), data.getEndTime());
                    Range<LocalTime> tempTimeRange = Range.closedOpen(item.getData().getStartTime(), item.getData().getEndTime());
                    if (newTimeRange.equals(tempTimeRange)) {
                        //时间相等的话  在比较 周
                        if (data.getDays().equals(item.getData().getDays())) {
                            //时间相等的话  在比较 周
                            throw new RuntimeException("当前添加的" + data + "已经存在了" + item);
                        }
                        if (data.getDays().containsAll(item.getData().getDays())) {
                            //新的包含 老的，那么新的 应该占用老的位置
                            Node newNode = new Node(data);
                            newNode.setNodes(Lists.newArrayList(item));
                            currentNode.nodes.set(i, newNode);
                            break;
                        } else if (item.getData().getDays().containsAll(data.getDays())) {
                            //老的 包含新的    那么继续和 老的 子节点比较
                            addFromNode(data, item);
                            break;
                        } else if (CollectionUtils.containsAny(item.getData().getDays(), data.getDays())) {
                            throw new RuntimeException("days交叉了\n新的：" + data + "\n当前：" + item);
                        }
                        currentNode.nodes.add(new Node(data));
                        break;
                    }
                    if (newTimeRange.encloses(tempTimeRange)) {
                        //新的包含 老的，那么新的 应该占用老的位置
                        Node newNode = new Node(data);
                        newNode.setNodes(Lists.newArrayList(item));
                        currentNode.nodes.set(i, newNode);
                        break;
                    } else if (tempTimeRange.encloses(newTimeRange)) {
                        //老的 包含新的    那么继续和 老的 子节点比较
                        addFromNode(data, item);
                        break;
                    } else if (tempTimeRange.isConnected(newTimeRange) && !tempTimeRange.intersection(newTimeRange).isEmpty()) {
                        throw new RuntimeException("time交叉了");
                    }
                    currentNode.nodes.add(new Node(data));
                    break;
                } else if (newDateRange.encloses(tempDateRange)) {
                    nonConnect = false;
                    //新的包含 老的，那么新的 应该占用老的位置
                    Node newNode = new Node(data);
                    newNode.setNodes(Lists.newArrayList(item));
                    currentNode.nodes.set(i, newNode);
                    break;
                } else if (tempDateRange.encloses(newDateRange)) {
                    //老的 包含新的    那么继续和 老的 子节点比较
                    nonConnect = false;
                    addFromNode(data, item);
                    break;
                } else if (tempDateRange.isConnected(newDateRange) && !tempDateRange.intersection(newDateRange).isEmpty()) {
                    throw new RuntimeException(tempDateRange + "date交叉了" + newDateRange);
                }
            }
            if (nonConnect) {
                currentNode.nodes.add(new Node(data));
            }
        }

        //遍历方法的重载
        public List<Rate> toListFromRoot() {
            List<Rate> rates = new ArrayList<Rate>();
            //i 表示当前节点的 深度
            int i = 0;
            this.toListFromNode(this.getRoot(), rates, i);
            return rates;
        }

        //循环Tree
        private void toListFromNode(Node currentNode, List<Rate> rates, int i) {
            List<Node> nodes = currentNode.nodes;
            if (nodes.isEmpty()) {
                return;
            }
            //i是currentNode 的深度，现在我们遍历的是  currentNode 的子节点，所以将深度+1，
            // 在for外层+1 是因为 所有的兄弟节点的深度应该都是一样的
            i++;
            for (Node node : nodes) {
                node.getData().setPriority(i);
                rates.add(node.getData());
                toListFromNode(node, rates, i);
            }
        }
    }
}





