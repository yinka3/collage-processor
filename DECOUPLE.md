### Decoupling:
    - In order to now ensure the controller is decoupled, we needed to run the same test as we did 
        for the View, there was no need to make any additional changes for this. However, what will
        be needed would be the filter package. This is because there was a lot of duplication and 
        for applying the filter in the controller so abtraction was needed.
    - To state, we could have easily just made duplicate code and everything would work perfectly,
        but we decided a project with no duplicated and as much abstraction as possible was more 
         needed. This was our design choice.
    - In order to allow for complete decoupling within our code, we decided to make more interfaces
        for our class. We added in an IHSL, ILayer, IImageUtil and IRGBA. This was all the interfaces
        needed to fully decouple our code.
    - We decided to not make an interface for RepresentationConverter as, it contains a lot more
        static methods than public methods so an interface was not needed. Additionally, when 
        testing out decoupling, the way we implemeneted the class, the code compiles without the
        need of an interface.
    - The interfaces needed to be sent are CollageView, Commands, GUI, IBlending, ICollage,
        IController, IFilter, IHSL, IRGBA, IImageUtil, ILayer.
    - All these interfaces are needed because they give the access to alll the implementations which
        then have all the methods that are needed for the model. This allows for a high-level 
        interaction, which I see as being the whole point of being decoupled. 
    - The classes needed were the view classes.