# Welcome to the Image Collage Project!
##### This is the documentation of Assignment 4 for OOD Spring 2023 at Northeastern University by
##### Abigail Rajadurai, Adeyinka Adedewe and Oceane Kenfack.

The Image Collage Project is a collage maker. This allows a user to create an image via layering
pictures and adding a variety of filters to a layer to customize and create a project. The features
available for use on the Image Collage maker include adding filters, saving and loading projects,
adding a new layer anywhere on the project, having two different color representations for
convenience and the ability to support multiple different types of image types (GUI only). All
necessary features for the project to work are all available for use and are fully functional. There
is no dependency on the features based on the type of UI are being ran, both GUI and the text 
version have all the features available.

Requirements: Java 11 or higher JRE and JUnit 4 for running the tests.

USEME: located in USEme.md file.

____
## RGBA

The base of our program is the RGBA class. This class represents a pixel of an image in the form of
an RGBA, where the pixel's Red, Green, Blue, Max, and Alpha components are represented as Integers
and Doubles. An RGBA can be 3 or 4 component. A 3 component RGBA only has red, green, and blue
values. A 4 component RGBA has all 3 color values and also an Alpha component representing how
much the pixel appears in an image.

#### The RGBA class implements the following public methods:
    - getRed, getGreen, getBlue, getAlpha, getMax return value of the RGBA's respective components.
    - Intensity, Luma, and Value represent the brightness of the pixel in 3 ways.
    - Transparency adjusts the opacity of the Pixel but its alpha value. /* We decided for internal
        issues to hard code this, as the transparency method for our program only worked when this
        happened */
    - Convert changes a 4 component RGBA to a 3 component RGBA.
    - Clamp is a helper method that rounds a negative number to 0 and a number above 255 to 255.
    - Copy simply returns a new RGBA that is identical.
    - toString returns a string representation of the RGBA.

There is a new Pixel implementation, the HSL, which is for hue, saturation, and lightness, we have
a class in which we can convert from HSL to RGB and vice versa. All the components are doubles.

#### This RepresentationConverter class implements the following public methods:
    - RGBToHSL which takes in an RGB component and converts it to HSL.
    - HSLtoRGB which takes in an HSL component and converts it to RGBA.

#### The HSL class then implements the following public method:
    - The getter methods for all the HSL components.
    - The clamp methods to bound the HSL components between certain values.

---
## ImageUtil

This is one of the most important classes for the whole model system. This class give the model the
ability to read in an image and turn it into a 2D array of RGBA components that can be then used in 
the model for modification. It also provides the ability to convert the modified 2D array of pixels
to any type of image style(PPM, jpg, png). 

#### The ImageUtil interface has the foloowing methods:
    - getHeight: returns the height of the image being rendered.
    - getWidth: returns the width of the image being rendered.
    - readPPM: takes in a ppm image and returns a 2D array of RGBA.
    - readFile: takes in a jpg or png image and returns a 2D array of RGBA.
    - getPixelAt: gets the x and y coordinates of an RGBA component.
    - savePPM: saves a 2D array of RGBA components and turns it into PPM-style image.
    - saveFile: saves a 2D array of RGBA components and turns it into a jpeg or png image.

___
## Layer
The next level of our program is a "layer", or a collection of images that are applied to the
collage together. Layers are manipulated as a whole. The representation of a layer is mainly
defined as a 2D array of pixels, which we thought was best suited for manipulation and an image
which is essentially is a layer is defined as a 2 dimensional plane of pixels.

#### The Layer Interface enumerates the following methods:
    - getName: returns the name of a layer.
    - getAlpha gets an alpha value for a layer.
    - newFilter adds a filter to a layer but does NOT apply it (the collage does not change as an
    image)
    - getNewRgba2 creates an exact copy of an image.
    - getFilter gets the filter option and applies to a layer.
    - setFilter sets the filter option to be that for the layer.
    - getBlend gets the blend options for that layer.
    - setBlend sets the blend options for that layer.
    - visualize returns the layer as a 2D array of all the pixels that make it up.
    - addImageToLayer adds an image to a layer.

___
## Processor

The highest level of the program is the processor itself, which manipulates a collage.
Processors have multiple commands that can be used to work on a project. The collage, we thought
at first was to be a list of layers but had given some difficulties in implementing some
methods, especially the addImageToLayer. After some view into java documentation,
a map specifically as a LinkedHashmap was best suited, as it allowed for easy access
to a layer just based off the string name. Additionally, the LinkedHashmap allowed for
ordered pairs as regular hashmaps are unordered. This was needed for the set blend portion.

#### These commands are:
    - AddImageToLayer adds an image to a layer.
    - AddLayer adds a layer to a collage.
    - CreateProject creates a new collage.
    - Load loads an image on to the processor.
    - Save collage saves the college as a active project so it can continue to be worked on.
    - SaveImage saves the collage as an image that cannot be edited.
    - Filter applies a filter to a layer.
    - Set Blend applies a filter to certain layers.
____
##  Filters
#### Layers are manipulated with filters. Filters have the following two public methods:
    - Apply applies a filter to a layer.
    - Apply applies the new blending filter to a layer.
    - toString produces a string representation of the filter.

#### This program supports the following filters:
    - BrightenByIntensity brightens all the pixels of a layer by manipulating their intensities.
    - BrightenByValue brightens all the pixels of a layer by manipulating their values.
    - BrightenByLuma brightens all the pixels of a layer by manipulating their lumas.
    - DarkenByIntensity darkens all the pixels of a layer by manipulating their intensities.
    - DarkenByValue darkens all the pixels of a layer by manipulating their values.
    - DarkenByLuma darkens all the pixels of a layer by manipulating their lumas.
    - FilterBlue changes the layer so only the blue components of each pixel in the layer remain.
    - FilterRed changes the layer so only the red components of each pixel in the layer remain.
    - FilterGreen changes the layer so only the green components of each pixel in the layer remain.

#### This program supports the new blending filters:
    - BlendingBrighten will take in all the composite layer from below a certain layer and
    it becomes a brighten to a certain point
    - BlendingDarken will take in all the composite layer from below a certain layer and
    will properly darken all the layers without turning it black.
    - BlendingDifference will take in all the composite layer from below a certain layer and
    have an inversion effect between all layers.
____
## Controller

    - The final portion of the project is a controller. A controller is a way for users to interact
        with the program to create and edit collages.

#### Instructions to enter commands:
     1) Start with making a new project with a width adn height dimension
     2) Add in a layer with a layer name onto the project
     3) Upload an image onto the layer with an offset position if you choose to
     4) Modify with desired filters. 
            - There are a a few filters you can use. See "This program supports..." section to see 
              the effects of each filter on an image)
     5) Repeat steps 2-4 until you are satisfied.
     6) Save the final image(It will be all your layers combined so be careful with 
       that), the file must end with an extension .ppm or .jpg or .png
     7) When there are more than 2 layers, a blending filter is also now applied.
              - The different blending filters are given above.
     8) Save your image as a collage, must be a filepath with an extension of .collage
    
     - Bonus, if you view your previous project, you can load the project up, again must end
       with an extension of .collage.

#### Controller Versions
    - There are two controller classes, one is used for accessing the inputs from the user through
        the terminal and then there is a controller for the gui to have visual componenets and for
        the user to input. Both versiond of the controllers behave the same way, one just has a more
        friendly UI to the user.

### Image Citation
- All the images that were used were found from the website:
  https://wallpapersden.com/anime-wallpapers/500x500/page/4/.