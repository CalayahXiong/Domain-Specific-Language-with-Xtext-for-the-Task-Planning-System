package org.xtext.example.mydsl.analysis;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.xtext.example.mydsl.MyDslStandaloneSetup;
import org.xtext.example.mydsl.myDsl.AndCondition;
import org.xtext.example.mydsl.myDsl.ShiftCondition;
import org.xtext.example.mydsl.myDsl.PrimaryCondition;
import org.xtext.example.mydsl.myDsl.Comparison;
import org.xtext.example.mydsl.myDsl.Condition;
import org.xtext.example.mydsl.myDsl.DifficultyCondition;
import org.xtext.example.mydsl.myDsl.OrCondition;

import org.xtext.example.mydsl.myDsl.Model;
import org.xtext.example.mydsl.myDsl.Task;
import org.xtext.example.mydsl.myDsl.Worker;
import org.xtext.example.mydsl.myDsl.Rule;
import org.xtext.example.mydsl.myDsl.SeniorityLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoTPS {

    private Model model;
    private Map< Worker, List<Task> > assignments = new HashMap<>();

    public AutoTPS(Model model) {
        this.model = model;
    }


    public static void main(String[] args) {
        // Load the TPS model 
        Model model = loadModel("src/data/tasks2.mydsl");

        // Create and run the AutoTPS tool
        AutoTPS autoTPS = new AutoTPS(model);
        autoTPS.model.evaluate();
    }

    private static Model loadModel(String filePath) {
    	MyDslStandaloneSetup.doSetup();
    	ResourceSet resourceSet = new ResourceSetImpl();
    	Resource resource = resourceSet.getResource(URI.createFileURI(filePath), true);
    	if (resource.getContents().isEmpty()) {
            throw new IllegalStateException("Resource is empty: " + filePath);
        }
    	Model model = (Model) resource.getContents().get(0);
        return model; 
    }
}