package com.github.mikesafonov.prometheus.telegram.dto;

import com.github.mikesafonov.prometheus.telegram.dto.enums.AlertLevel;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AlertTest {
    @Nested
    class GetLabelByName {
        @Test
        void shouldReturnEmptyLabel() {
            Alert alert = new Alert();
            alert.setLabels(Collections.emptyMap());

            assertFalse(alert.getLabelByName("some name").isPresent());
        }

        @Test
        void shouldReturnEmptyLabelBecauseLabelsIsNull(){
            Alert alert = new Alert();
            alert.setLabels(null);
            assertFalse(alert.getLabelByName("some name").isPresent());
        }

        @Test
        void shouldReturnLabel() {
            Alert alert = new Alert();
            Map<String, String> labels = new HashMap<>();
            labels.put("label1", "label1_value");
            labels.put("label2", "label2_value");
            alert.setLabels(labels);

            assertEquals("label1_value", alert.getLabelByName("label1").get());
        }
    }
    @Nested
    class GetAnnotationByName{
        @Test
        void shouldReturnEmptyAnnotation() {
            Alert alert = new Alert();
            alert.setAnnotations(Collections.emptyMap());

            assertFalse(alert.getLabelByName("some name").isPresent());
        }

        @Test
        void shouldReturnEmptyAnnotationBecauseAnnotationsIsNull(){
            Alert alert = new Alert();
            alert.setAnnotations(null);
            assertFalse(alert.getLabelByName("some name").isPresent());
        }

        @Test
        void shouldReturnAnnotation() {
            Alert alert = new Alert();
            Map<String, String> annotations = new HashMap<>();
            annotations.put("label1", "label1_value");
            annotations.put("label2", "label2_value");
            alert.setAnnotations(annotations);

            assertEquals("label1_value", alert.getAnnotationByName("label1").get());
        }
    }


    @Test
    void shouldReturnAlertName(){
        Alert alert = new Alert();
        Map<String, String> labels = new HashMap<>();
        labels.put("alertname", "label1_value");
        labels.put("label2", "label2_value");
        alert.setLabels(labels);

        assertEquals("label1_value", alert.getAlertName().get());
    }

    @Test
    void shouldReturnJob(){
        Alert alert = new Alert();
        Map<String, String> labels = new HashMap<>();
        labels.put("job", "label1_value");
        labels.put("label2", "label2_value");
        alert.setLabels(labels);

        assertEquals("label1_value", alert.getJob().get());
    }

    @Test
    void shouldReturnProject(){
        Alert alert = new Alert();
        Map<String, String> labels = new HashMap<>();
        labels.put("project", "label1_value");
        labels.put("label2", "label2_value");
        alert.setLabels(labels);

        assertEquals("label1_value", alert.getProject().get());
    }

    @Test
    void shouldReturnService(){
        Alert alert = new Alert();
        Map<String, String> labels = new HashMap<>();
        labels.put("service", "label1_value");
        labels.put("label2", "label2_value");
        alert.setLabels(labels);

        assertEquals("label1_value", alert.getService().get());
    }

    @Test
    void shouldReturnTitle(){
        Alert alert = new Alert();
        Map<String, String> annotations = new HashMap<>();
        annotations.put("title", "label1_value");
        annotations.put("label2", "label2_value");
        alert.setAnnotations(annotations);

        assertEquals("label1_value", alert.getTitle().get());
    }

    @Test
    void shouldReturnResolvedTitle(){
        Alert alert = new Alert();
        Map<String, String> annotations = new HashMap<>();
        annotations.put("resolved_title", "label1_value");
        annotations.put("label2", "label2_value");
        alert.setAnnotations(annotations);

        assertEquals("label1_value", alert.getResolvedTitle().get());
    }

    @Test
    void shouldReturnDescription(){
        Alert alert = new Alert();
        Map<String, String> annotations = new HashMap<>();
        annotations.put("description", "label1_value");
        annotations.put("label2", "label2_value");
        alert.setAnnotations(annotations);

        assertEquals("label1_value", alert.getDescription().get());
    }

    @Test
    void shouldReturnResolvedDescription(){
        Alert alert = new Alert();
        Map<String, String> annotations = new HashMap<>();
        annotations.put("resolved_description", "label1_value");
        annotations.put("label2", "label2_value");
        alert.setAnnotations(annotations);

        assertEquals("label1_value", alert.getResolvedDescription().get());
    }

    @ParameterizedTest
    @EnumSource(AlertLevel.class)
    void shouldReturnExpectedLevel(AlertLevel level){
        Alert alert = new Alert();
        Map<String, String> annotations = new HashMap<>();
        annotations.put("level", level.toString());
        alert.setAnnotations(annotations);

        assertEquals(level, alert.getLevel().get());
    }
}
