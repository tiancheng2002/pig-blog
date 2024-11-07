"use strict";

+function ($) {
    "use strict";

    function AppendAndRemoveCharAnimation(chars) {
        var delayOver = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 1000;
        var delayBegin = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;
        var delayBlink = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 400;
        var cls = arguments.length > 4 && arguments[4] !== undefined ? arguments[4] : "animating-word";
        var elem = this;
        var main = $("<span class=\"".concat(cls, "\"></span>"));
        var decoration = $("<span class=\"blinking-decoration\">_</span>");
        var moveOfDecoration = null;
        var animate = null;
        var characters = chars;

        function startDecoration() {
            moveOfDecoration = setInterval(function () {
                if (decoration.css("opacity") === "0") {
                    decoration.css("opacity", "1");
                } else {
                    decoration.css("opacity", "0");
                }
            }, 350);
        }

        function pauseDecoration() {
            decoration.css("opacity", "1");
            clearTimeout(moveOfDecoration);
        }

        startDecoration();

        function startAnimation() {
            var index = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 0;
            var curr = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : null;
            var len = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : null;

            if (index === characters.length) {
                index = 0;
            }

            var character = curr ? curr : characters[index].slice();
            var length = len ? len : character.length;
            var count = 0;
            animate = setInterval(function () {
                pauseDecoration();

                if (character.length > 0) {
                    var char = character.shift();
                    main.html(main.html() + char);

                    if (!character.length) {
                        clearTimeout(animate);
                        startDecoration();
                        setTimeout(function () {
                            startAnimation(index, character, length);
                        }, delayOver);
                    }
                } else {
                    var inner = main.html();
                    inner = inner.slice(0, inner.length - 1);
                    main.html(inner);
                    count++;

                    if (count === length) {
                        clearTimeout(animate);
                        startDecoration();
                        setTimeout(function () {
                            startAnimation(index + 1);
                        }, delayBegin);
                    }
                }
            }, delayBlink);
        }

        function setSuitFontSize() {
            decoration.css("fontSize", parseInt(main.css("fontSize")) * 1.5 + "px");
        }

        function appendDecoration() {
            main.after(decoration);
        }

        function appendSpan() {
            elem.after(main);
        }

        function appendElements() {
            appendSpan();
            appendDecoration();
        }

        function init() {
            appendElements();
            setSuitFontSize();
            startAnimation();
        }

        init();
    }

    $.prototype.appendAndRemoveCharAnimation = AppendAndRemoveCharAnimation;
}(jQuery);