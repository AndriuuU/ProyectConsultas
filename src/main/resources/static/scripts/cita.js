class MiCita extends HTMLElement {
    constructor() {
        super();
        this.shadowDOM = this.attachShadow({mode: 'open'});
    }

	
    static get observedAttributes() { return ['data', 'citaid', 'deletecita']; }

    attributeChangedCallback(attr, oldVal, newVal) {
        if (this.attr == "data") this.data = newVal;

        if (this.attr = "citaid") this.citaId = newVal;

        if (this.attr = "deletecita") this.deleteCita = newVal;
      }

    connectedCallback() {
        this.data = this.getAttribute('data') || '';
        this.citaId = this.getAttribute('citaid') || '';
        this.deleteCita = this.getAttribute('deletecita') || '';

        this.render();
    }

    getTemplate() {
        const dataEl = this.data.split("&");
        const [hour, day, dayNumber, month, year, service] = dataEl;

        const template = document.createElement('template');
        template.innerHTML = `
            <style>
	        	.cita-wrapper {
	        		background-color: rgb(252, 252, 252);
	        		width: 500px;
	        		margin: 2em auto;
	        		border-radius: 1em;
	        		padding: 1em;
	        		display: flex;
	        		flex-direction: row;
	        		line-height: 1;
	        	}
            
	        	.cita-icon {
	        		margin-left: 1em;
	        		margin-right: 3em;
	        		background-color: white;
	        		border-radius: 1em;
	        		width: 100px;
	        		height: 100px;
	        	}

				.icon-body {
					border: 1px solid #dddddd;
    				border-bottom-left-radius: 1em;
    				border-bottom-right-radius: 1em;
					height: 75%;
				}
            
	        	.icon-header {
	        		background-color: rgb(235, 37, 37);
	        		border-top-left-radius: 1em;
	        		border-top-right-radius: 1em;
	        		color: white;
	        		font-size: 24px;
	        		text-align: center;
	        	}
            
	        	#month {
	        		margin: 0;
	        		margin-top: 0.3em;
	        		font-size: 16px;
	        		text-align: center;
	        	}
            
	        	#day {
	        		font-size: 46px;
	        		margin: 0;
	        		text-align: center;
	        	}
            
	        	h1 {
	        		text-align: center;
	        	}
            
	        	h2 {
	        		font-size: 26px;
	        		margin: 0;
	        	}
            
	        	hr {
	        		width: 600px;
	        		margin: 1em auto;
	        	}
            
	        	.cita-body p {
	        		margin: 0;
	        	}
            
	        	#hour {
	        		font-size: 20px;
	        	}
            
	        	#service {
	        		margin-top: 0.5em;
	        	}
            
	        	.delete-btn button {
	        		background-color: crimson;
	        		color: white;
	        		border: none;
	        		border-radius: 0.5em;
	        		padding: 0.3em;
	        		font-size: 14px;
	        	}
	        </style>

            <div class="cita-wrapper">
                <div class="cita-icon">
                    <div class="icon-header" id="year">${year}</div>
                    <div class="icon-body">
                        <p id="month">${month}</p>
                        <p id="day">${dayNumber}</p>
                    </div>
                </div>
                <div class="cita-body">
                    <h2>Información sobre tu cita</h2>
                    <i id="hour">${day} a las ${hour}</i>
                    <p id="service">${service}</p>
                </div>

                <a href="${this.deleteCita}" class="delete-btn" style="position: relative;">
                    <button type="button" style="position: absolute; bottom: -3px; left: -30px; cursor: pointer;">Eliminar cita</button>
                </a>
            </div>
        `;
        return template;
    }

    render() {
        this.shadowRoot.appendChild(this.getTemplate().content.cloneNode(true));
    }
}

customElements.define("mi-cita", MiCita);