package com.mixtoler.paint.controlers;


import com.mixtoler.paint.shapes.Shape;
import com.mixtoler.paint.shapes.ShapeFactory;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFileChooser;



@RestController
@RequestMapping("/shape")
public class MainController {

    private ShapesWrapper shapesWrapper = new ShapesWrapper();
    private HashMap<Integer, Shape> shapesHash = shapesWrapper.getShapesHash();
    private HistoryManager historyManager = new HistoryManager();
    private final LoadStoreManager loadStoreManager = new LoadStoreManager();
    private final ShapeFactory shapeFactory = new ShapeFactory();
    
    

    @RequestMapping("/draw")
    public void drawShape(@RequestParam int id, @RequestParam String type, @RequestParam String color,
                          @RequestParam double[] locationParams, @RequestParam double[] sizeParams){
        Shape shape = shapeFactory.getShape(type);
        shape.setID(id);
        shape.setColor(color);
        shape.setLocation(locationParams);
        shape.setSize(sizeParams);
        shapesHash.put(id, shape);
        historyManager.add(shapesHash);
        
    }

    @RequestMapping("/edit")
    public void editShape(@RequestParam int id, @RequestParam String type, @RequestParam String color,
                          @RequestParam double[] locationParams, @RequestParam double[] sizeParams){
        Shape shape = shapesHash.get(id);
        shape.setColor(color);
        shape.setLocation(locationParams);
        shape.setSize(sizeParams);
        historyManager.add(shapesHash);
    }

    @RequestMapping("/delete")
    public void deleteShape(@RequestParam int id){
        shapesHash.remove(id);
        historyManager.add(shapesHash);
        System.out.println(shapesHash);
        System.out.println(id);
    }

    @RequestMapping("/copy")
    public void copyShape(@RequestParam int oldID, @RequestParam int newID,
                          @RequestParam double[] newLocationParams){
        Shape oldShape = shapesHash.get(oldID);
        drawShape(newID, oldShape.getColor(), oldShape.getType(),
                newLocationParams, oldShape.getSizeParams());
    }

    @RequestMapping("/undo")
    public Collection<Shape> undo(){
        HashMap<Integer, Shape> oldShapesHash = shapesHash;
        shapesHash = historyManager.undo();
        if(shapesHash == null) {
            shapesHash = oldShapesHash;
            return null;
        }
        
        return shapesHash.values();
    }

    @RequestMapping("/redo")
    public Collection<Shape> redo(){
        HashMap<Integer, Shape> oldShapesHash = shapesHash;
        shapesHash = historyManager.redo();
        if(shapesHash == null) {
            shapesHash = oldShapesHash;
           
            return null;
        }
       
        return shapesHash.values();
    }

    @RequestMapping("/storeXML")
    public void storeXML(){
    	String fileInfo = "";
    	JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   
		     fileInfo = chooser.getSelectedFile().getAbsolutePath();
		}
        try{
        	fileInfo += "\\shapesXML.xml";
            loadStoreManager.storeXML(shapesWrapper, fileInfo);
        }catch(Exception ignored) {System.out.println(ignored);}
    }

    @RequestMapping("/loadXML")
    public Collection<Shape> loadXML(){
    	String fileInfo = "";
    	JFileChooser file = new JFileChooser();
        file.setMultiSelectionEnabled(true);
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setFileHidingEnabled(false);
        if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
           java.io.File f = file.getSelectedFile();
           fileInfo = f.getPath();
        }
        try{
            shapesWrapper = loadStoreManager.loadXML(fileInfo);
            shapesHash = shapesWrapper.getShapesHash();
            historyManager = new HistoryManager();
            historyManager.add(shapesHash);
            return shapesHash.values();
        }catch(Exception ignored) {}
        return null;
    }

    @RequestMapping("/storeJSON")
    public void storeJSON(){
    	String fileInfo = "";
    	JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   
		     fileInfo = chooser.getSelectedFile().getAbsolutePath();
		}
        try{
        	fileInfo += "\\shapesJSON.json";
            loadStoreManager.storeJSON(shapesHash, fileInfo);
        }catch(Exception ignored) {}
    }

    @RequestMapping("/loadJSON")
    public Collection<Shape> loadJSON(){
    	String fileInfo = "";
    	JFileChooser file = new JFileChooser();
        file.setMultiSelectionEnabled(true);
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setFileHidingEnabled(false);
        if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
           java.io.File f = file.getSelectedFile();
           fileInfo = f.getPath();
        }
        try{
            shapesHash = loadStoreManager.loadJSON(fileInfo);
            historyManager = new HistoryManager();
            historyManager.add(shapesHash);
            return shapesHash.values();
        }catch(Exception ignored) {}
        return null;
    }
}
