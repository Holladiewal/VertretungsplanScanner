package com.beepbeat.HTMLLib;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tag {

    private List<Attribute> attributes;
    private String name, Inner;

    /**
     *
     * @param name Name of the tag (e.g. a, link, div, span, ...)
     * @param attributes Any {@link Attribute} the tag may have
     * @param Inner The part between the opening and the closing of a tag (also known as InnerHTML).
     */

    public Tag(String name, List<Attribute> attributes, String Inner){
        this.name = name;
        this.attributes = attributes;
        this.Inner = Inner;
    }

    /**
     * Should only be used, when {@link this.fromString} is used
     */
    public Tag(){
        attributes = new ArrayList<>();

    }


    /**
     * @param in A String containing one(!) HTML-Tag
     * @throws IllegalArgumentException when param in appears to not contain an HTML-Tag
     */
    public void fromString(String in){
        if (!in.contains("<") || !in.contains("</") || !in.contains(">")) throw new IllegalArgumentException("Input String must contain < or > or </");

        List<String> inL = Arrays.asList(in.split("<"));

        Inner = inL.get(1).split(">")[1];
        String Tag = inL.get(1).split(">")[0];

        this.name = Tag.split(" ")[0];
        String[] TagA = Tag.split(" ");

        attributes.clear();
        for (String s : TagA){
            if (!s.contains("=")) continue;
            attributes.add(new Attribute(s.split("=")[0], s.split("=")[1]));
        }
    }

    /**
     * @param in A String containing one(!) HTML-Tag
     * @param tag An existing instance of the Tag-Class
     * @throws IllegalArgumentException when param in appears to not contain an HTML-Tag
     * @return An overwritten Tag
     */
    public Tag fromString(Tag tag, String in){
        if (!in.contains("<") || !in.contains("</") || !in.contains(">")) throw new IllegalArgumentException("Input String must contain < or > or </");

        List<String> inL = Arrays.asList(in.split("<"));

        tag.Inner = inL.get(1).split(">")[1];
        String Tag = inL.get(1).split(">")[0];

        tag.name = Tag.split(" ")[0];
        String[] TagA = Tag.split(" ");

        for (String s : TagA){
            if (!s.contains("=")) continue;
            tag.attributes.add(new Attribute(s.split("=")[0], s.split("=")[1]));
        }

        return tag;
    }

    /**
     * @return Name of the tag (e.g. a, link, div, span, ...)
     */
    public String getName() {
        return name;
    }

    /**
     * @return A List of {@link Attribute}
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * @return The innerHTML of the Tag
     */
    public String getInner() {
        return Inner;
    }

    @Override
    public String toString(){
        String returnV = "";
        if (name.contains("<"))
        returnV = returnV + name;
        else
        returnV = returnV + "<" + name;

        for (Attribute a : attributes){
            returnV = returnV + " " + a.toString();
        }


        returnV = returnV + ">" + Inner + "</" + name.replace("<", "") + ">";
        return returnV;
    }
}
