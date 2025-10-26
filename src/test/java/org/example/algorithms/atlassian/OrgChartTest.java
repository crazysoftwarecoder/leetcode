package org.example.algorithms.atlassian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrgChartTest {

    @Test
    public void test() {
        OrgChart orgChart = new OrgChart();

        OrgChart.Node a = new OrgChart.Node("A", null, null);
        OrgChart.Node b = new OrgChart.Node("B", null, null);
        OrgChart.Node c = new OrgChart.Node("C", null, null);

        OrgChart.Node hr = new OrgChart.Node("HR",  a, b);
        OrgChart.Node engg = new OrgChart.Node("Engg", c, null);
        OrgChart.Node company = new OrgChart.Node("Company", hr, engg);

        a.setParent(hr);
        b.setParent(hr);
        c.setParent(engg);

        hr.setParent(company);
        engg.setParent(company);

        Assertions.assertEquals(company.getData(), orgChart.getCommonAncestor(company, List.of("A", "B", "C")).getData());
    }

    @Test
    public void test2() {
        OrgChart orgChart = new OrgChart();

        OrgChart.Node a = new OrgChart.Node("A", null, null);
        OrgChart.Node b = new OrgChart.Node("B", null, null);
        OrgChart.Node c = new OrgChart.Node("C", null, null);

        OrgChart.Node hr = new OrgChart.Node("HR",  a, b);
        OrgChart.Node engg = new OrgChart.Node("Engg", c, null);
        OrgChart.Node company = new OrgChart.Node("Company", hr, engg);

        a.setParent(hr);
        b.setParent(hr);
        c.setParent(engg);

        hr.setParent(company);
        engg.setParent(company);

        Assertions.assertEquals(hr.getData(), orgChart.getCommonAncestor(company, List.of("A", "B")).getData());
    }
}

//            Company
//          /          \
//         HR          Engg
//        /  \          /
//       A    B         C

