window.addEventListener('scroll', e => {
	document.documentElement.style.setProperty('--scrollTop', `${this.scrollY}px`) // Update method
})
gsap.registerPlugin(ScrollTrigger, ScrollSmoother)
ScrollSmoother.create({
	wrapper: '.wrapper',
	content: '.content'
})


function anotherView(param) {
	const elem = document.getElementById(param);
	elem.classList.toggle("another-view")
	elem.classList.toggle("hidden")
}

function toggleList(param) {
	const elem = document.getElementById(param);
	elem.classList.toggle("hidden")
}