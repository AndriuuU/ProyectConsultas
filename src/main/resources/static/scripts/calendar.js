let months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
let days = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"];
let timesAvailable = ["09:00am", "09:45am", "10:30am", "11:15am", "12:00pm", "12:45pm", "13:30pm"];

// Calendar
document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        height: 'auto',
        showNonCurrentDates: false,
        selectable: true,
        locale: 'es',
        select: function(info) {
            let currentDay = new Date();
            let daySelected = info.start;
            let selectedDate = new Date(info.start);

            if (daySelected >= currentDay) {

                let timeDiv = document.getElementById("available-times-div");

                while (timeDiv.firstChild) {
                    timeDiv.removeChild(timeDiv.lastChild);
                }

                //Heading - Date Selected
                let h4 = document.createElement("h4");
                let h4node = document.createTextNode(
                    days[daySelected.getDay()] + ", " +
                    months[daySelected.getMonth()] + " " + 
                    daySelected.getDate());
                h4.appendChild(h4node);

                timeDiv.appendChild(h4);

                //Time Buttons
                for (let i = 0; i < timesAvailable.length; i++) {
                    let timeSlot = document.createElement("div");
                    timeSlot.classList.add("time-slot");

                    let timeBtn = document.createElement("button");

                    let btnNode = document.createTextNode(timesAvailable[i]); 
                    timeBtn.classList.add("time-btn");

                    timeBtn.appendChild(btnNode);
                    timeSlot.appendChild(timeBtn);

                    timeDiv.appendChild(timeSlot);

                    // When time is selected
                    let last = null;
                    timeBtn.addEventListener("click", function() {
                        if (last != null) {
                            console.log(last);
                            last.parentNode.removeChild(last.parentNode.lastChild);
                        }

                        let serviciosOption = document.querySelector('#servicio');

                        let hour = timeBtn.innerText;
                        let day = days[daySelected.getDay()];
                        let dayNumber = selectedDate.getDate()
                        let month = months[daySelected.getMonth()];
                        let year = selectedDate.getFullYear();
                        let service = serviciosOption.options[serviciosOption.selectedIndex].text;

                        let confirmForm = document.createElement("form");
                        let confirmBtn = document.createElement("button");
                        confirmForm.method = "get";
                        confirmBtn.type = "submit";
                        confirmBtn.innerText = "Confirmar";

                        confirmForm.classList.add("confirm-btn");

                        confirmForm.appendChild(confirmBtn);
                        this.parentNode.appendChild(confirmForm);

                        let data = `${hour}&${day}&${dayNumber}&${month}&${year}&${service}`;
                        console.log(data);

                        confirmBtn.addEventListener("click", function(_event) { 
                            _event.preventDefault();

                            confirmForm.action = `/cita/usuario/get/cita/${data}/${daySelected.toJSON()}`;
                            confirmForm.submit();
                        });
                        last = this;
                    });
                }

                let containerDiv = document.getElementsByClassName("container")[0];
                containerDiv.classList.add("time-div-active");
                
                document.getElementById("calendar-section").style.flex = "2";

                timeDiv.style.display = "initial";

            } else {alert("La fecha no puede ser anterior o igual al día actual.");}
        },
    });
    calendar.render();
});

