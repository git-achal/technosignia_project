new Chart(document.getElementById("studentChart"), {

    type: "bar",

    data: {

        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],

        datasets: [{

            label: "Students",

            data: [12, 19, 8, 15, 22, 18]

        }]

    },

    options: {

        responsive: true,

        maintainAspectRatio: false

    }

});